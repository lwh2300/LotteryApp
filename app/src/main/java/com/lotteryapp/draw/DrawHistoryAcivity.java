package com.lotteryapp.draw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.lotteryapp.MainActivity;
import com.lotteryapp.R;
import com.lotteryapp.mine.RulesCheckedAdapter;
import com.lotteryapp.mine.RulesListValue;

import java.util.ArrayList;
import java.util.List;

public class DrawHistoryAcivity  extends Activity {
    private ImageView backBtn;
    private ListView rulesLv;
    private RulesCheckedAdapter adapter;
    private List<RulesListValue> list;

    private String[] radioTexts = {"1.名称：千位、百位相同时","2.名称：千位、百位相同时","3.名称：千位、百位相同时","4.名称：千位、百位相同时","5.名称：千位、百位相同时","6.名称：千位、百位相同时","7.名称：千位、百位相同时","8.名称：千位、百位相同时","9.名称：千位、百位相同时","10.名称：千位、百位相同时","11.名称：千位、百位相同时"};
    private String[] radioDates = {"2019-07-22 15:11","2019-07-23 15:11","2019-07-24 15:11","2019-07-25 15:11","2019-07-23 15:11","2019-07-24 15:11","2019-07-25 15:11","2019-07-23 15:11","2019-07-24 15:11","2019-07-25 15:11","2019-07-25 15:11"};
    private String[] preShows = {"最近10期出现","最近10期出现","","","","","","","","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_history);

        //返回
        backBtn = (ImageView) findViewById(R.id.back_mine);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawHistoryAcivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });

        //规库列表
        rulesLv = (ListView) findViewById(R.id.rules_lv);
        list = new ArrayList<RulesListValue>();
        for (int i=0; i< radioTexts.length; i++){
            RulesListValue data = new RulesListValue(radioTexts[i],radioDates[i],preShows[i]);
            list.add(data);
        }

        adapter = new RulesCheckedAdapter(this,list,R.layout.draw_history_item);
        rulesLv.setAdapter(adapter);

        rulesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                adapter.setChecked(position);
                adapter.notifyDataSetInvalidated();
            }
        });

        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
