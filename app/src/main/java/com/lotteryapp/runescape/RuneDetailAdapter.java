package com.lotteryapp.runescape;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotteryapp.R;

import java.util.List;

public class RuneDetailAdapter extends BaseAdapter {
    Context context;
    List<RuneMsgDetailValue> data;

    public RuneDetailAdapter(Context context,List<RuneMsgDetailValue> data,int resource){
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
        View view = LayoutInflater.from(context).inflate(R.layout.rune_detail_item,parent,false);

        ImageView photo = (ImageView) view.findViewById(R.id.photo);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView time = (TextView) view.findViewById(R.id.time);

        photo.setImageResource(data.get(position).getPhoto());
        name.setText(data.get(position).getName());
        content.setText(data.get(position).getContext());
        time.setText(data.get(position).getTime());

        return view;
    }
}
