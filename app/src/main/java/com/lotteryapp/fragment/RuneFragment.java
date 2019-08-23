package com.lotteryapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lotteryapp.R;
import com.lotteryapp.draw.DrawActivity;
import com.lotteryapp.draw.DrawDialog;
import com.lotteryapp.mine.RulesLibraryActivity;

import java.util.ArrayList;
import java.util.List;

public class RuneFragment extends Fragment{
    private ViewPager rune_context;
    private TextView tabSeven;       //七星彩（预测）
    private TextView tabFive;        //排列五（预测）
    private List<TextView> tTabs = new ArrayList<>();

    private RuneSevenFragment runeSevenFragment;         //七星彩（预测）
    private RuneFiveFragment runeFiveFragment;           //排列五（预测）
    private List<Fragment> tFragments = new ArrayList<Fragment>();

    private static final int COLOR_DEFAULT = Color.parseColor("#ff000000");
    private static final int COLOR_SELECT = Color.parseColor("#FFea1720");

    private ImageView issue;
    private DrawDialog runeDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_rune,container,false);

        rune_context = (ViewPager) view.findViewById(R.id.rune_context);
        initView(view);

        initViewPageAdapter();
        initEvents();

        //发布
        issue = (ImageView) view.findViewById(R.id.issue);
        issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runeDialog = new DrawDialog(RuneFragment.this.getContext(),R.layout.runescape_issue);
                runeDialog.show();

                runeDialog.getWindow().findViewById(R.id.issue_item2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RuneFragment.this.getContext(), RulesLibraryActivity.class);
                        startActivity(intent);
                        runeDialog.hide();
                    }
                });

                runeDialog.getWindow().findViewById(R.id.issue_item1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RuneFragment.this.getContext(), DrawActivity.class);
                        startActivity(intent);
                        runeDialog.hide();
                    }
                });
            }
        });
        return view;
    }

    private void initEvents() {
        for (int i=0;i<tTabs.size();i++){
            TextView tabView = tTabs.get(i);
            final int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rune_context.setCurrentItem(finalI,false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void initViewPageAdapter() {
        rune_context.setOffscreenPageLimit(2);

        rune_context.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return tFragments.get(position);
            }

            @Override
            public int getCount() {
                return tFragments.size();
            }

        });

        rune_context.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setCurrentTab(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView(View view) {
        tabSeven = (TextView) view.findViewById(R.id.tab_seven);
        tabFive = (TextView) view.findViewById(R.id.tab_five);

        tTabs.add(tabSeven);
        tTabs.add(tabFive);
        setCurrentTab(0);

        runeSevenFragment = new RuneSevenFragment();
        runeFiveFragment = new RuneFiveFragment();

        tFragments.add(runeSevenFragment);
        tFragments.add(runeFiveFragment);
    }

    private void setCurrentTab(int pos){
        for (int i=0;i<tTabs.size();i++){
            TextView tabView = tTabs.get(i);
            if (i == pos){
                tabView.setTextColor(Color.parseColor("#DA251D"));
                tabView.setBackgroundResource(R.drawable.tabs_underline_select);
            }else{
                tabView.setTextColor(Color.parseColor("#000000"));
                tabView.setBackgroundResource(0);
            }
        }
    }
}
