package com.lotteryapp.draw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotteryapp.R;
import com.lotteryapp.mine.RulesCheckedAdapter;
import com.lotteryapp.mine.RulesListValue;

import java.util.List;

public class DrawHistoryAdapter extends BaseAdapter {
    private Context context;
    private List<RulesListValue> data;
    private LayoutInflater inflater;
    private int checked = -1;

    public DrawHistoryAdapter(Context context, List<RulesListValue> data,int resource){
        this.context = context;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setChecked(int checked){
        this.checked = checked;
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
    public View getView(int position, View view, ViewGroup parent) {
        RulesCheckedAdapter.ViewHolder holder;

        if(view==null){
            holder = new RulesCheckedAdapter.ViewHolder();
            view = inflater.inflate(R.layout.draw_history_item, null);
            holder.iv = view.findViewById(R.id.radio_icon);
            view.setTag(holder);
        }else{
            holder = (RulesCheckedAdapter.ViewHolder) view.getTag();
        }

        if(checked == position){
            holder.iv.setImageResource(R.mipmap.radio_select);
        }else{
            holder.iv.setImageResource(R.mipmap.radio);
        }

        TextView radioText = view.findViewById(R.id.radio_text);
        TextView radioDate = view.findViewById(R.id.radio_time);
        TextView preShow = view.findViewById(R.id.pre_show);

        radioText.setText(data.get(position).getItemName());
        radioDate.setText(data.get(position).getItemTime());
        preShow.setText(data.get(position).getItemTip());

        return view;
    }

    class ViewHolder{
        public ImageView iv;
    }
}
