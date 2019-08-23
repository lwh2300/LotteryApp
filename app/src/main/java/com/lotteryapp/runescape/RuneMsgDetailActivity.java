package com.lotteryapp.runescape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lotteryapp.MainActivity;
import com.lotteryapp.R;
import com.lotteryapp.draw.DrawFiveList;
import com.lotteryapp.draw.DrawFiveListAdapter;
import com.lotteryapp.draw.DrawListAdapter;
import com.lotteryapp.draw.DrawListValue;
import com.lotteryapp.test.RuneDrawFragment;
import com.lotteryapp.view.TabView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class RuneMsgDetailActivity extends AppCompatActivity {
    private ImageView back;             //返回箭头
    private ImageView persionCntBtn;    //头像
    private LinearLayout contextBtn;    //内容容器
    private LinearLayout contextWrp;    //展开内容容器
    private ImageView closeContext;     //下箭头
    private LinearLayout goodBtn;       //点赞容器
    private ImageView goodIcon;         //点赞图标
    private LinearLayout followBtn;     //跟帖容器

    //七星彩
    private ListView msgDrawLv;
    private DrawListAdapter drawListAdapter;
    private List<DrawListValue> drawListValues;
    private int[] lv0s = {19063,19064,19065,19066,19067,19068,19069,19070,19071,19072,19073,19074,19075,19063,19064,19065,19066,19067,19068,19069,19070,19071,19072,19073,19074,19075};
    private int[] lv1s = {13,11,16,28,21,18,20,19,23,15,13,20,16,13,11,16,28,21,18,20,19,23,15,13,20,16};
    private int[] lv2s = {1,1,4,6,0,6,6,4,2,8,2,0,8,1,1,4,6,0,6,6,4,2,8,2,0,8};
    private int[] lv3s = {2,7,3,7,8,6,6,2,4,6,2,9,3,2,7,3,7,8,6,6,2,4,6,2,9,3};
    private int[] lv4s = {8,1,3,7,8,3,8,4,9,1,4,5,3,8,1,3,7,8,3,8,4,9,1,4,5,3};
    private int[] lv5s = {2,2,6,8,5,3,0,9,8,0,5,6,2,2,2,6,8,5,3,0,9,8,0,5,6,2};
    private int[] lv6s = {6,2,2,6,9,8,1,9,4,4,5,2,6,6,2,2,6,9,8,1,9,4,4,5,2,6};
    private int[] lv7s = {4,3,2,1,7,0,4,7,8,9,2,0,5,4,3,2,1,7,0,4,7,8,9,2,0,5};
    private int[] lv8s = {8,3,7,0,4,9,7,2,2,6,9,5,5,8,3,7,0,4,9,7,2,2,6,9,5,5};

    //跟帖
    private RuneMsgDetailAdapter detailAdapter;
    private List<RuneMsgDetailValue> detailList;
    private ListView listView;
    private int[] photos = {R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo};
    private String[] names = {"张三","李四","王五","赵六"};
    private String[] contents = {"赞","赞","赞","赞"};
    private String[] times = {"2019年7月14日 16:06","2019年7月15日 16:06","2019年7月16日 16:06","2019年7月17日 16:06"};

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

    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rune_msg_details);

        gestureDetector = new GestureDetector(RuneMsgDetailActivity.this,onGestureListener);

        //返回
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RuneMsgDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //七星彩 设置listview
//        msgDrawLv = (ListView) findViewById(R.id.rune_draw_msg);
        drawListValues = new ArrayList<DrawListValue>();
        for (int i = 0; i< lv0s.length;i++){
            DrawListValue data = new DrawListValue(lv0s[i],lv1s[i],lv2s[i],lv3s[i],lv4s[i],lv5s[i],lv6s[i],lv7s[i],lv8s[i]);
            drawListValues.add(data);
        }
        drawListAdapter = new DrawListAdapter(this,drawListValues, R.layout.draw_seven_list_item);
        RuneDrawFragment fragment1=RuneDrawFragment.instance(drawListAdapter);
//        msgDrawLv.setAdapter(drawListAdapter);


        //排列五
        //fiveList = (ListView) findViewById(R.id.rune_draw_msg);
        fiveLists = new ArrayList<DrawFiveList>();
        for (int i = 0; i< fiveLv0_1.length;i++){
            DrawFiveList listData = new DrawFiveList(fiveLv0_1[i],fiveLv0_2[i],fiveLv0_3[i],fiveLv1[i],fiveLv2[i],fiveLv3[i],fiveLv4[i],fiveLv5[i],fiveLv6[i]);
            fiveLists.add(listData);
        }
        fiveListAdapter = new DrawFiveListAdapter(this,fiveLists,R.layout.draw_five_list_item);
        RuneDrawFragment fragment2=RuneDrawFragment.instance(fiveListAdapter);
        //fiveList.setAdapter(fiveListAdapter);

        final List<Fragment> fragments=new ArrayList();
        fragments.add(fragment1);
        fragments.add(fragment2);

        ViewPager vp=(ViewPager) findViewById(R.id.rune_draw_viewpager);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        //点击头像 - 个人中心
        persionCntBtn = (ImageView) findViewById(R.id.per_center_btn);
        persionCntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RuneMsgDetailActivity.this,PersionCenterActivity.class);
                startActivity(intent);
            }
        });

        //点击内容 - 展开
        contextBtn = (LinearLayout) findViewById(R.id.context_btn);
        contextWrp = (LinearLayout) findViewById(R.id.context_wrp);
        contextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contextWrp.setVisibility(View.VISIBLE);
            }
        });

        //点击下箭头 - 折叠
        closeContext = (ImageView) findViewById(R.id.close_context);
        closeContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contextWrp.setVisibility(View.GONE);
            }
        });

        //点击跟帖图标
        followBtn = (LinearLayout) findViewById(R.id.follow_up);
        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contextWrp.setVisibility(View.VISIBLE);
            }
        });

        //点赞
        goodBtn = (LinearLayout) findViewById(R.id.good);
        goodIcon = (ImageView) findViewById(R.id.good_icon);
        goodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodIcon.setImageResource(R.mipmap.good_select);
            }
        });


        //跟帖内容
        listView = (ListView) findViewById(R.id.detail_list);
        detailList = new ArrayList<RuneMsgDetailValue>();
        for (int i = 0; i < names.length; i ++){
            RuneMsgDetailValue runeValue = new RuneMsgDetailValue(photos[i],names[i],contents[i],times[i]);
            detailList.add(runeValue);
        }
        detailAdapter = new RuneMsgDetailAdapter(this,detailList,R.layout.rune_msg_details);
        listView.setAdapter(detailAdapter);
        setListViewHeightBasedOnChildren(listView);

    }

    //listview自定义高度显示
    public void setListViewHeightBasedOnChildren(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null){
            return;
        }
        int totalHeight = 0;
        for (int i=0;i<listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
    }

    private GestureDetector.OnGestureListener onGestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();

            if (x > 0) {
                doResult(RIGHT);
            } else if (x < 0) {
                doResult(LEFT);
            }
            return true;
        }
    };

    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void doResult(int action) {
        switch (action) {
            case RIGHT:
                msgDrawLv.setVisibility(View.GONE);
                fiveList.setVisibility(View.VISIBLE);
                break;

            case LEFT:
                msgDrawLv.setVisibility(View.VISIBLE);
                fiveList.setVisibility(View.GONE);
                break;
        }
    }





}
