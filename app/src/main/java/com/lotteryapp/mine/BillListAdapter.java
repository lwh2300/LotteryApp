package com.lotteryapp.mine;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotteryapp.R;

import java.util.List;

public class BillListAdapter extends BaseAdapter {
    Context context;
    List<BillListValue> data;

    public BillListAdapter(Context context, List<BillListValue> data,int resource){
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_bill_details_item, parent,false);

        TextView type = (TextView) view.findViewById(R.id.bill_type);
        TextView time = (TextView) view.findViewById(R.id.bill_time);
        TextView money = (TextView) view.findViewById(R.id.money);

        type.setText(data.get(position).getType());
        time.setText(data.get(position).getTime());
        money.setText(data.get(position).getMoney()+"");

        if(money.getText().charAt(0) == '-'){
            money.setTextColor(Color.parseColor("#DA251D"));
        }else{
            money.setTextColor(Color.parseColor("#21824c"));
        }

        return view;
    }
}
