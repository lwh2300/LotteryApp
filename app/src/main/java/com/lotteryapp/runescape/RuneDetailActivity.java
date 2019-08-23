package com.lotteryapp.runescape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.lotteryapp.MainActivity;
import com.lotteryapp.R;

import java.util.ArrayList;
import java.util.List;

public class RuneDetailActivity extends Activity {
    private ImageView back;

    private RuneDetailAdapter adapter;
    private ListView listView;
    private List<RuneMsgDetailValue> list;

    private int[] photos = {R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo};
    private String[] names = {"张三","李四","王五","赵六","李四","王五","赵六"};
    private String[] contents = {"赞","赞","赞","赞","赞","赞","赞"};
    private String[] times = {"2019年7月14日 16:06","2019年7月15日 16:06","2019年7月16日 16:06","2019年7月17日 16:06","2019年7月15日 16:06","2019年7月16日 16:06","2019年7月17日 16:06"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rune_detail);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RuneDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        listView = (ListView) findViewById(R.id.detail_list);
        list = new ArrayList<RuneMsgDetailValue>();
        for (int i = 0; i < names.length; i ++){
            RuneMsgDetailValue runeValue = new RuneMsgDetailValue(photos[i],names[i],contents[i],times[i]);
            list.add(runeValue);
        }
        adapter = new RuneDetailAdapter(this,list,R.layout.rune_detail_item);
        listView.setAdapter(adapter);


    }
}
