package com.lotteryapp.runescape;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lotteryapp.R;

import java.util.List;

public class RuneMsgAdapter extends BaseAdapter {
    Context context;
    List<RuneMsgValue> data;
    public RuneMsgAdapter(Context context,List<RuneMsgValue> data,int resource){
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
        View view = LayoutInflater.from(context).inflate(R.layout.msg2_list_item, parent,false);
        ImageView image = (ImageView) view.findViewById(R.id.seven_photo);
        TextView name = (TextView) view.findViewById(R.id.seven_name);
        TextView date = (TextView) view.findViewById(R.id.seven_time);
        ImageView draw = (ImageView) view.findViewById(R.id.seven_draw);
        TextView content = (TextView) view.findViewById(R.id.seven_content);

        TextView shareNum = (TextView) view.findViewById(R.id.share_num);
        TextView goodNum = (TextView) view.findViewById(R.id.good_num);
        TextView followUpNum = (TextView) view.findViewById(R.id.follow_up_num);

        image.setImageResource(data.get(position).getImage());
        name.setText(data.get(position).getName());
        date.setText(data.get(position).getDate());
        draw.setImageResource(data.get(position).getDraw());
        content.setText(data.get(position).getContext());

        shareNum.setText(data.get(position).getShareNum() + "");
        goodNum.setText(data.get(position).getGoodNum() + "");
        followUpNum.setText(data.get(position).getShareNum() + "");

        //点击头像跳转到个人中心
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PersionCenterActivity.class);
                context.startActivity(intent);
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PersionCenterActivity.class);
                context.startActivity(intent);
            }
        });

        LinearLayout detailBtn = (LinearLayout) view.findViewById(R.id.seven_detail);

        if (draw.getDrawable() == null){
            draw.setVisibility(View.GONE);

            //无规图 - 详情
            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,RuneDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }else{
            //详情
            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,RuneMsgDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        LinearLayout goodClick = (LinearLayout) view.findViewById(R.id.good_click);
        goodClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView goodIcon = (ImageView) view.findViewById(R.id.good_icon);

                goodIcon.setImageResource(R.mipmap.good_select);
            }
        });

        LinearLayout followClick = (LinearLayout) view.findViewById(R.id.follow_click);
        followClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }

}
