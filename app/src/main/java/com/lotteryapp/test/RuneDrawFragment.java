package com.lotteryapp.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lotteryapp.R;
import com.lotteryapp.draw.DrawFiveList;
import com.lotteryapp.draw.DrawFiveListAdapter;
import com.lotteryapp.draw.DrawListAdapter;
import com.lotteryapp.draw.DrawListValue;

import java.util.ArrayList;
import java.util.List;


public class RuneDrawFragment extends Fragment {
    BaseAdapter adapter;

    public RuneDrawFragment() {
    }

    public static RuneDrawFragment instance( BaseAdapter adapter){
        RuneDrawFragment runeDrawFragment=new RuneDrawFragment();
        runeDrawFragment.adapter=adapter;
        return  runeDrawFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_rune_draw,container,false);
        ListView ls=(ListView)view.findViewById(R.id.rune_draw);
        ls.setAdapter(adapter);
        return view;
    }

}
