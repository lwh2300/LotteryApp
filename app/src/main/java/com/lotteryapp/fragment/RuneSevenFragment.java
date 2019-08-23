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

public class RuneSevenFragment extends Fragment {
    private ListView msg2List;
    private RuneMsgAdapter adapter;
    private List<RuneMsgValue> list;

    private int[] sevenPhoto = {R.mipmap.photo,R.mipmap.photo,R.mipmap.photo,R.mipmap.photo};
    private String[] sevenName = {"天涯石头","天涯石头1","天涯石头2","天涯石头3"};
    private String[] sevenTime = {"2019年7月14日 16:06","2019年7月15日 16:06","2019年7月16日 16:06","2019年7月17日 16:06"};
    private int[] sevenDraw = {R.mipmap.draw_img,0 , R.mipmap.draw_img,0};
    private String[] sevenContent = {"2323期123头567尾巴、123头567尾巴、123头567尾巴、123头567尾巴","2324期123头567尾巴","2325期123头567尾巴","2326期123头567尾巴"};

    private int[] sevenShareNum = {0,1,2,3};
    private int[] sevenGoodNum = {4,11,45,0};
    private int[] sevenFollowUpNum = {0,9,11,25};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg2_seven,container,false);

        msg2List = (ListView) view.findViewById(R.id.msg2List);
        list = new ArrayList<RuneMsgValue>();
        for (int i=0;i<sevenPhoto.length;i++){
            RuneMsgValue runeMsgValue = new RuneMsgValue(sevenPhoto[i],sevenName[i],sevenTime[i],sevenDraw[i],sevenContent[i],sevenShareNum[i],sevenGoodNum[i],sevenFollowUpNum[i]);
            list.add(runeMsgValue);
        }
        adapter = new RuneMsgAdapter(RuneSevenFragment.this.getContext(),list,R.layout.msg2_list_item);
        msg2List.setAdapter(adapter);

        return view;
    }
}
