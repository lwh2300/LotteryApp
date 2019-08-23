package com.lotteryapp.runescape;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lotteryapp.MainActivity;
import com.lotteryapp.R;

import java.util.ArrayList;
import java.util.List;

public class PersionCenterActivity extends Activity {
    private ImageView back;
    private ListView perLv;
    private PersionCenterAdapter adapter;
    private List<PersionCenterValue> list;

    private String[] perContexts = {"2323期123头567尾巴","2324期123头567尾巴","2325期123头567尾巴","2325期123头567尾巴"};
    private int[] perDraws = {R.mipmap.draw_img,R.mipmap.draw_img,0,0};
    private String[] perTypes = {"七星彩","排列五","论道交流","论道交流"};
    private String[] perTimes = {"2019年7月14日 16:06","2019年7月15日 16:06","2019年7月16日 16:06","2019年7月17日 16:06"};
    private int[] perShares = {0,5,2,2};
    private int[] perGoods = {5,2,02,0};
    private int[] perFollowUps = {5,5,2,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rune_perion_center);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersionCenterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        perLv = (ListView) findViewById(R.id.per_lv);
        list = new ArrayList<PersionCenterValue>();
        for (int i = 0; i < perContexts.length; i++){
            PersionCenterValue persionCenterValue = new PersionCenterValue(perContexts[i],perDraws[i],perTypes[i],perTimes[i],perShares[i],perGoods[i],perFollowUps[i]);
            list.add(persionCenterValue);
        }

        adapter = new PersionCenterAdapter(this,list,R.layout.rune_persion_list);
        perLv.setAdapter(adapter);


        //关注状态
        final TextView attention = (TextView) findViewById(R.id.attention);
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attention.getText().toString().equals("关注")){
                    attention.setText("取消关注");
                    attention.setTextColor(Color.parseColor("#666666"));
                    attention.setBackgroundResource(R.drawable.attention_wrp1);
                }else if (attention.getText().toString().equals("取消关注")){
                    attention.setText("关注");
                    attention.setTextColor(Color.parseColor("#DA251D"));
                    attention.setBackgroundResource(R.drawable.attention_wrp);
                }
            }
        });
    }
}
