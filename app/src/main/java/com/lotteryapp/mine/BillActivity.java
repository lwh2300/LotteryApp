package com.lotteryapp.mine;

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

public class BillActivity extends Activity {
    private ImageView billBack;
    private ListView billList;
    private BillListAdapter adapter;
    private List<BillListValue> list;

    private String[] types = {"充值","分享收益","消费（运算）","消费（空间)"};
    private String[] times = {"7月26日 08：09","7月27日 08：09","7月28日 08：09","7月29日 08：09"};
    private int[] moneys = {1452,1235,-56,-52};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_bill);

        //返回
        billBack = (ImageView) findViewById(R.id.bill_back);
        billBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        billList = (ListView) findViewById(R.id.bill_list);
        list = new ArrayList<BillListValue>();
        for (int i = 0; i< types.length; i++){
            BillListValue billListValue = new BillListValue(types[i],times[i],moneys[i]);
            list.add(billListValue);
        }
        adapter = new BillListAdapter(this,list,R.layout.mine_bill_details_item);
        billList.setAdapter(adapter);


        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
