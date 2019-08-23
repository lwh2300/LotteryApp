package com.lotteryapp.draw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lotteryapp.MainActivity;
import com.lotteryapp.R;
import com.lotteryapp.dialog.SpinnerDialog;

import java.util.ArrayList;
import java.util.List;

public class DrawActivity extends Activity{
    private ImageView backBtn;            //返回图标
    private LinearLayout history;         //规库
    private TextView showBtn;             //显示
    private TextView labelBtn;            //标注
    private LinearLayout draw;            //AI画规
    private LinearLayout contentWrp;      //底部工具栏容器
    private TextView closeToolBtn;        //关闭底部工具栏

    private LinearLayout colorTool;       //颜色工具栏
    private LinearLayout colorBtn;        //颜色按钮
    private ImageView closeColor;         //关闭颜色栏

    private LinearLayout textTool;        //文字工具栏
    private LinearLayout textBtn;         //文字按钮
    private ImageView closeText;          //关闭文字栏

    private LinearLayout delLine;         //删线
    boolean isChange = true;

    private LinearLayout toolWrp;         //工具栏

    //下拉列表
    private LinearLayout spinnerWrp;
    private TextView spinnerText;
    private SpinnerDialog spinnerDialog;

    private SpinnerDialog drawDialog;

    private RelativeLayout displayWrp;


    //七星彩
    private ListView lv;
    private DrawListAdapter adapter;
    private List<DrawListValue> list;
    private int[] lv0s = {19063,19064,19065,19066,19067,19068,19069,19070,19071,19072,19073,19074,19075,19063,19064,19065,19066,19067,19068,19069,19070,19071,19072,19073,19074,19075};
    private int[] lv1s = {13,11,16,28,21,18,20,19,23,15,13,20,16,13,11,16,28,21,18,20,19,23,15,13,20,16};
    private int[] lv2s = {1,1,4,6,0,6,6,4,2,8,2,0,8,1,1,4,6,0,6,6,4,2,8,2,0,8};
    private int[] lv3s = {2,7,3,7,8,6,6,2,4,6,2,9,3,2,7,3,7,8,6,6,2,4,6,2,9,3};
    private int[] lv4s = {8,1,3,7,8,3,8,4,9,1,4,5,3,8,1,3,7,8,3,8,4,9,1,4,5,3};
    private int[] lv5s = {2,2,6,8,5,3,0,9,8,0,5,6,2,2,2,6,8,5,3,0,9,8,0,5,6,2};
    private int[] lv6s = {6,2,2,6,9,8,1,9,4,4,5,2,6,6,2,2,6,9,8,1,9,4,4,5,2,6};
    private int[] lv7s = {4,3,2,1,7,0,4,7,8,9,2,0,5,4,3,2,1,7,0,4,7,8,9,2,0,5};
    private int[] lv8s = {8,3,7,0,4,9,7,2,2,6,9,5,5,8,3,7,0,4,9,7,2,2,6,9,5,5};

    //排列五
    private DrawFiveListAdapter fiveListAdapter;
    private ListView fiveList;
    private List<DrawFiveList> fiveLists;
    private String[] fiveLv0_1 = {"日","一","二","三","四","五","六","日","一","二","三","四","五","六","日","一","二","三","四","五","六","日","一","二","三","四"};
    private String[] fiveLv0_2 = {"06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16","06-16"};
    private int[] fiveLv0_3 = {19063,19064,19065,19066,19067,19068,19069,19070,19071,19072,19073,19074,19075,19063,19064,19065,19066,19067,19068,19069,19070,19071,19072,19073,19074,19075};
    private int[] fiveLv1 = {13,11,16,28,21,18,20,19,23,15,13,20,16,13,11,16,28,21,18,20,19,23,15,13,20,16};
    private int[] fiveLv2 = {1,1,4,6,0,6,6,4,2,8,2,0,8,1,1,4,6,0,6,6,4,2,8,2,0,8};
    private int[] fiveLv3 = {1,1,4,6,0,6,6,4,2,8,2,0,8,1,1,4,6,0,6,6,4,2,8,2,0,8};
    private int[] fiveLv4 = {2,7,3,7,8,6,6,2,4,6,2,9,3,2,7,3,7,8,6,6,2,4,6,2,9,3};
    private int[] fiveLv5 = {8,1,3,7,8,3,8,4,9,1,4,5,3,8,1,3,7,8,3,8,4,9,1,4,5,3};
    private int[] fiveLv6 = {2,2,6,8,5,3,0,9,8,0,5,6,2,2,2,6,8,5,3,0,9,8,0,5,6,2};

    //显示用参数
    MyView myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_draw);

        displayWrp = (RelativeLayout) findViewById(R.id.display_wrp);

        //七星彩
        lv = (ListView) findViewById(R.id.lv);
        list=new ArrayList<DrawListValue>();
        for (int i = 0; i< lv0s.length;i++){
            DrawListValue data = new DrawListValue(lv0s[i],lv1s[i],lv2s[i],lv3s[i],lv4s[i],lv5s[i],lv6s[i],lv7s[i],lv8s[i]);
            list.add(data);
        }
        adapter = new DrawListAdapter(this,list, R.layout.draw_seven_list_item);
        lv.setAdapter(adapter);
        lv.setVisibility(View.GONE);
        getBitmap(lv);
        LinearLayout fl=(LinearLayout) findViewById(R.id.ff);
        myView=init(lv);
        fl.addView(myView);


        //排列五
        fiveList = (ListView) findViewById(R.id.five_list);
        fiveLists = new ArrayList<DrawFiveList>();
        for (int i = 0; i< fiveLv0_1.length;i++){
            DrawFiveList listData = new DrawFiveList(fiveLv0_1[i],fiveLv0_2[i],fiveLv0_3[i],fiveLv1[i],fiveLv2[i],fiveLv3[i],fiveLv4[i],fiveLv5[i],fiveLv6[i]);
            fiveLists.add(listData);
        }
        fiveListAdapter = new DrawFiveListAdapter(this,fiveLists,R.layout.draw_five_list_item);
        fiveList.setAdapter(fiveListAdapter);


        //返回
        backBtn = (ImageView) findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //规库
        history = (LinearLayout) findViewById(R.id.draw_history_item);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawActivity.this,DrawHistoryAcivity.class);
                startActivity(intent);
            }
        });

        //AI画规
        draw = (LinearLayout) findViewById(R.id.draw);
        contentWrp = (LinearLayout) findViewById(R.id.context);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentWrp.setVisibility(View.VISIBLE);
                draw.setVisibility(View.GONE);
                history.setVisibility(View.GONE);
                myView.scrollEnable=-1;
            }
        });


        toolWrp = (LinearLayout) findViewById(R.id.tool_wrp);
        //删线
        delLine = (LinearLayout) findViewById(R.id.del_line);
        delLine.setBackgroundResource(R.drawable.del_line_bg1);
        delLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChange){
                    delLine.setBackgroundResource(R.drawable.del_line_bg);
                }else{
                    delLine.setBackgroundResource(R.drawable.del_line_bg1);
                }
                isChange = !isChange;
            }
        });

        //颜色工具栏
        colorBtn = (LinearLayout) findViewById(R.id.color_btn);
        colorTool = (LinearLayout) findViewById(R.id.color_tool);
        closeColor = (ImageView) findViewById(R.id.close_color);
        colorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolWrp.setVisibility(View.GONE);
                colorTool.setVisibility(View.VISIBLE);
                delLine.setBackgroundResource(R.drawable.del_line_bg1);
            }
        });

        //关闭颜色栏
        closeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorTool.setVisibility(View.GONE);
                toolWrp.setVisibility(View.VISIBLE);
            }
        });

        //文字工具栏
        textBtn = (LinearLayout) findViewById(R.id.text_btn);
        textTool = (LinearLayout) findViewById(R.id.text_tool);
        closeText = (ImageView) findViewById(R.id.close_text);
        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolWrp.setVisibility(View.GONE);
                textTool.setVisibility(View.VISIBLE);
                delLine.setBackgroundResource(R.drawable.del_line_bg1);
            }
        });

        //关闭文字栏
        closeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTool.setVisibility(View.GONE);
                toolWrp.setVisibility(View.VISIBLE);

            }
        });

        //关闭底部工具栏
        closeToolBtn = (TextView) findViewById(R.id.draw_close_btn);
        closeToolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentWrp.setVisibility(View.GONE);
                draw.setVisibility(View.VISIBLE);
                history.setVisibility(View.VISIBLE);
                delLine.setBackgroundResource(R.drawable.del_line_bg1);
                toolWrp.setVisibility(View.VISIBLE);
                textTool.setVisibility(View.GONE);
                colorTool.setVisibility(View.GONE);
                myView.scrollEnable=1;
            }
        });

        //显示
        showBtn = (TextView) findViewById(R.id.show_item);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawDialog = new SpinnerDialog(DrawActivity.this,R.layout.draw_show,displayWrp);
                drawDialog.show();

                contentWrp.setVisibility(View.GONE);
                displayWrp.setVisibility(View.GONE);
            }
        });

        //标注
        labelBtn = (TextView) findViewById(R.id.tip_item);
        labelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawDialog = new SpinnerDialog(DrawActivity.this,R.layout.draw_label,displayWrp);
                drawDialog.show();

                contentWrp.setVisibility(View.GONE);
                displayWrp.setVisibility(View.GONE);
            }
        });


        //下拉列表
        spinnerWrp = (LinearLayout) findViewById(R.id.spinner_wrp);
        spinnerText = (TextView) findViewById(R.id.spinner_text);
        spinnerWrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerDialog = new SpinnerDialog(DrawActivity.this,R.layout.draw_spinner,displayWrp);
                spinnerDialog.show();

                displayWrp.setVisibility(View.GONE);


                //七星彩
                spinnerDialog.getWindow().findViewById(R.id.seven_spinner).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fiveList.setVisibility(View.GONE);
                        lv.setVisibility(View.VISIBLE);
                        spinnerText.setText("七星彩");
                        spinnerDialog.hide();

                        displayWrp.setVisibility(View.VISIBLE);
                    }
                });

                //排列五
                spinnerDialog.getWindow().findViewById(R.id.five_spinner).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        fiveList.setVisibility(View.VISIBLE);
                        lv.setVisibility(View.GONE);
                        spinnerText.setText("排列五");
                        spinnerDialog.hide();

                        displayWrp.setVisibility(View.VISIBLE);
                    }
                });
            }
        });


        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public MyView init(ListView lv){
        int count=lv.getAdapter().getCount();
        List<Bitmap>bitmaps = new ArrayList<>();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        Integer screenWidth = outMetrics.widthPixels;
        int MeasureSpecWidth=View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.EXACTLY);
        int MeasureSpecHeight=View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int totalHeight=0;
        for(int i=0;i<count;i++){
            View child=lv.getAdapter().getView(i,null,lv);
            child.measure(MeasureSpecWidth,MeasureSpecHeight);
            child.layout(0,0,child.getMeasuredWidth(),child.getMeasuredHeight());
            child.setDrawingCacheEnabled(true);
            child.buildDrawingCache();
            bitmaps.add(child.getDrawingCache());
            totalHeight+=child.getMeasuredHeight();
        }
        Bitmap Listbitmap=Bitmap.createBitmap(screenWidth,totalHeight,Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(Listbitmap);
        canvas.drawColor(Color.WHITE);
        int y=0;
        for (int i=0;i<bitmaps.size();i++){
            Bitmap bitmap = bitmaps.get(i);
            canvas.drawBitmap(bitmap,0,y,new Paint());
            y+=bitmap.getHeight();
            bitmap.recycle();
        }
        Listbitmap.getHeight();
        myView= new MyView(this,Listbitmap,totalHeight/count);
        return myView;
    }


    public void getBitmap(final ListView lv){
            lv.measure(0,0);
          //  lv.layout(0,0,lv.getMeasuredWidth(),lv.getMeasuredHeight());
            int mw=lv.getMeasuredWidth();
            int mh=lv.getMeasuredHeight();

            int w=lv.getWidth();
            int h=lv.getHeight();
            ViewTreeObserver vto=lv.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    lv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int a=lv.getMeasuredWidth();
                    int b=lv.getMeasuredHeight();
                }
            });

            Bitmap bm=Bitmap.createBitmap(mw,mh,Bitmap.Config.RGB_565);
            Canvas c=new Canvas(bm);
            c.drawColor(Color.WHITE);
            lv.draw(c);
            int flag=bm==null?1:0;
    }

}
