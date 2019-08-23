package com.lotteryapp.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotteryapp.R;

import java.util.List;

public class RulesCheckedAdapter extends BaseAdapter {
    private Context context;
    private List<RulesListValue> data;
    private LayoutInflater inflater;
    private int checked = -1;

    public RulesCheckedAdapter(Context context, List<RulesListValue> data,int resource){
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
        ViewHolder holder;

        if(view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.mine_ruleslabrary_list_item, null);
            holder.iv = view.findViewById(R.id.radio_icon);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
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

        if (preShow.getText().toString().equals("")){
            preShow.setVisibility(View.GONE);
        }
        return view;
    }

    public static class ViewHolder{
        public ImageView iv;
    }
}
