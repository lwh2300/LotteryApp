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

public class PersionCenterAdapter extends BaseAdapter {
    Context context;
    List<PersionCenterValue> data;
    boolean isChange = true;
    private TextView perContext;
    private ImageView perDraw;
    private TextView perTime;
    private TextView perType;
    private TextView perShare;
    private TextView perGood;
    private TextView perFollowUp;

    private LinearLayout goodClick;

    public PersionCenterAdapter(Context context,List<PersionCenterValue> data,int resource){
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
        View view = LayoutInflater.from(context).inflate(R.layout.rune_persion_list, parent,false);

        perContext = (TextView) view.findViewById(R.id.per_context);
        perDraw = (ImageView) view.findViewById(R.id.per_draw);
        perTime = (TextView) view.findViewById(R.id.per_time);
        perType = (TextView) view.findViewById(R.id.per_type);
        perShare = (TextView) view.findViewById(R.id.share_num);
        perGood = (TextView) view.findViewById(R.id.good_num);
        perFollowUp = (TextView) view.findViewById(R.id.follow_up_num);

        perContext.setText(data.get(position).getPerContext());
        perDraw.setImageResource(data.get(position).getPerDraw());
        perTime.setText(data.get(position).getPerDate());
        perType.setText(data.get(position).getPerType());
        perShare.setText(data.get(position).getPerShare() + "");
        perGood.setText(data.get(position).getPerGood() + "");
        perFollowUp.setText(data.get(position).getPerFollowUp() + "");

        //无图片时隐藏

        goodClick = (LinearLayout) view.findViewById(R.id.good_click);
        LinearLayout centerDetail = (LinearLayout) view.findViewById(R.id.center_details);
        if (perDraw.getDrawable() == null){
            perDraw.setVisibility(View.GONE);

            //跳转到详情页面
            centerDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,RuneDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }else {
            //跳转到详情页面
            centerDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,RuneMsgDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }




        goodClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView goodIcon = (ImageView) view.findViewById(R.id.good_icon);

                if (isChange) {
                    goodIcon.setImageResource(R.mipmap.good_select);
                } else {
                    goodIcon.setImageResource(R.mipmap.good);
                }
                isChange = !isChange;
            }
        });

        return view;
    }
}
