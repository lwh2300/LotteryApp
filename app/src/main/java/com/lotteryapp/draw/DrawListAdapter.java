package com.lotteryapp.draw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.lotteryapp.R;
import java.util.List;

public class DrawListAdapter extends BaseAdapter {
    List<DrawListValue> data;
    Context context;

    public DrawListAdapter(Context context,List<DrawListValue> data, int resource) {
        this.data=data;
        this.context=context;
    }


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
        View view= LayoutInflater.from(context).inflate(R.layout.draw_seven_list_item,parent,false);
        if(position % 2 == 0){
            view.setBackgroundResource(R.drawable.draw_blue_bgcolor);
        }else{
            view.setBackgroundResource(R.drawable.draw_white_bgcolor);
        }

        if ((position+1) % 4 == 0 ){
            view.setBackgroundResource(R.drawable.draw_bottom_orange_border);
        }

        TextView lv0 = view.findViewById(R.id.lv0);
        TextView lv1 = view.findViewById(R.id.lv1);
        TextView lv2 = view.findViewById(R.id.lv2);
        TextView lv3 = view.findViewById(R.id.lv3);
        TextView lv4 = view.findViewById(R.id.lv4);
        TextView lv5 = view.findViewById(R.id.lv5);
        TextView lv6 = view.findViewById(R.id.lv6);
        TextView lv7 = view.findViewById(R.id.lv7);
        TextView lv8 = view.findViewById(R.id.lv8);

        lv0.setText(data.get(position).getLv0()+"");
        lv1.setText(data.get(position).getLv1()+"");
        lv2.setText(data.get(position).getLv2()+"");
        lv3.setText(data.get(position).getLv3()+"");
        lv4.setText(data.get(position).getLv4()+"");
        lv5.setText(data.get(position).getLv5()+"");
        lv6.setText(data.get(position).getLv6()+"");
        lv7.setText(data.get(position).getLv7()+"");
        lv8.setText(data.get(position).getLv8()+"");

        return view;
    }
}
