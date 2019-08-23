package com.lotteryapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lotteryapp.R;
import com.lotteryapp.runescape.PersionCenterActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttentionFragment extends Fragment {
    private LinearLayout loginBtn;
    private LinearLayout loginWrp;
    private LinearLayout contextWrp;

    //七星彩
    private ListView sevenAttention;
    private SimpleAdapter adapterSeven;
    private List<Map<String,Object>> sevenList;
    private Map<String,Object> sevenMap;

    private int[] seven_photo = {R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo};
    private String[] seven_item1 = {"天涯石头","天涯石头","天涯石头","天涯石头"};
    private int[] seven_item2 = {R.mipmap.win,0,R.mipmap.win,R.mipmap.lack};
    private String[] seven_item3 = {"10中6","10中6","10中6","10中6"};
    private String[] seven_item4 = {"最大连中3","最大连中3","最大连中3","最大连中3"};
    private String[] seven_item6 = {"2019-07-22 14:54","2天前","2019-07-22 14:54","2019-07-22 14:54"};

    //排列五
    private ListView fiveAttention;
    private SimpleAdapter adapterFive;
    private List<Map<String,Object>> fiveList;
    private Map<String,Object> fiveMap;
    private int[] five_photo = {R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo};
    private String[] five_item1 = {"天涯石头天涯石头天涯石头天涯石头天涯石头天涯石头","天涯石头","天涯石头","天涯石头"};
    private int[] five_item2 = {R.mipmap.win,0,R.mipmap.win,R.mipmap.lack};
    private String[] five_item3 = {"10中6","10中6","10中6","10中6"};
    private String[] five_item4 = {"最大连中3","最大连中3","最大连中3","最大连中3"};
    private String[] five_item6 = {"2019-07-22 14:54","2天前","2019-07-22 14:54","2019-07-22 14:54"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_attention,container,false);

        //七星彩
        sevenAttention = (ListView) view.findViewById(R.id.attention_seven);
        sevenList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i< seven_item1.length;i++){
            sevenMap = new HashMap<String, Object>();
            sevenMap.put("seven_photo",seven_photo[i]);
            sevenMap.put("seven_item1",seven_item1[i]);
            sevenMap.put("seven_item2",seven_item2[i]);
            sevenMap.put("seven_item3",seven_item3[i]);
            sevenMap.put("seven_item4",seven_item4[i]);
            sevenMap.put("seven_item6",seven_item6[i]);
            sevenList.add(sevenMap);
        }
        String[] from = {"seven_photo","seven_item1","seven_item2","seven_item3","seven_item4","seven_item6"};
        int[] to = {R.id.msg_photo,R.id.msg_item1,R.id.msg_item2,R.id.msg_item3,R.id.msg_item4,R.id.msg_item6};
        adapterSeven = new SimpleAdapter(getActivity(),sevenList,R.layout.msg1_list_item,from,to);
        sevenAttention.setAdapter(adapterSeven);
        setListViewHeightBasedOnChildren(sevenAttention);

        sevenAttention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PersionCenterActivity.class);
                startActivity(intent);
            }
        });

        //排列五
        fiveAttention = (ListView) view.findViewById(R.id.attention_five);
        fiveList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i< five_item1.length;i++){
            fiveMap = new HashMap<String, Object>();
            fiveMap.put("five_photo",five_photo[i]);
            fiveMap.put("five_item1",five_item1[i]);
            fiveMap.put("five_item2",five_item2[i]);
            fiveMap.put("five_item3",five_item3[i]);
            fiveMap.put("five_item4",five_item4[i]);
            fiveMap.put("five_item6",five_item6[i]);
            fiveList.add(fiveMap);
        }
        String[] fiveFrom = {"five_photo","five_item1","five_item2","five_item3","five_item4","five_item6"};
        int[] fiveTo = {R.id.msg_photo,R.id.msg_item1,R.id.msg_item2,R.id.msg_item3,R.id.msg_item4,R.id.msg_item6};
        adapterFive = new SimpleAdapter(getActivity(),fiveList,R.layout.msg1_list_item,fiveFrom,fiveTo);
        fiveAttention.setAdapter(adapterFive);
        setListViewHeightBasedOnChildren(fiveAttention);

        fiveAttention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PersionCenterActivity.class);
                startActivity(intent);
            }
        });


        loginBtn = (LinearLayout) view.findViewById(R.id.login_btn);
        loginWrp = (LinearLayout) view.findViewById(R.id.login_wrp);
        contextWrp = (LinearLayout) view.findViewById(R.id.context_wrp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWrp.setVisibility(View.GONE);
                contextWrp.setVisibility(View.VISIBLE);
            }
        });


        return view;
    }

    //listview自定义高度显示
    public void setListViewHeightBasedOnChildren(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null){
            return;
        }
        int totalHeight = 0;
        for (int i=0;i<listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
    }
}
