package com.lotteryapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lotteryapp.R;
import com.lotteryapp.runescape.RuneMsgAdapter;
import com.lotteryapp.runescape.RuneMsgValue;

import java.util.ArrayList;
import java.util.List;

public class RuneFiveFragment  extends Fragment {
    private ListView msg2List;
    private RuneMsgAdapter adapter;
    private List<RuneMsgValue> list;

    private int[] fivePhoto = {R.mipmap.photo,R.mipmap.photo};
    private String[] fiveName = {"天涯石头","天涯石头1"};
    private String[] fiveTime = {"2019年7月14日 16:06","2019年7月15日 16:06"};
    private int[] fiveDraw = {R.mipmap.draw_img,0 };
    private String[] fiveContent = {"2323期123头567尾巴、123头567尾巴、123头567尾巴、123头567尾巴","2324期123头567尾巴"};

    private int[] fiveShareNum = {0,0};
    private int[] fiveGoodNum = {99,5};
    private int[] fiveFollowUpNum = {110,520};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg2_five,container,false);

        msg2List = (ListView) view.findViewById(R.id.msg2List);
        list = new ArrayList<RuneMsgValue>();
        for (int i=0;i<fivePhoto.length;i++){
            RuneMsgValue runeMsgValue = new RuneMsgValue(fivePhoto[i],fiveName[i],fiveTime[i],fiveDraw[i],fiveContent[i],fiveShareNum[i],fiveGoodNum[i],fiveFollowUpNum[i]);
            list.add(runeMsgValue);
        }

        adapter = new RuneMsgAdapter(getActivity(),list,R.layout.msg2_list_item);
        msg2List.setAdapter(adapter);
        return view;
    }
}
