package com.lotteryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lotteryapp.draw.DrawActivity;
import com.lotteryapp.fragment.AttentionFragment;
import com.lotteryapp.fragment.MineFragment;
import com.lotteryapp.fragment.NewsFragment;
import com.lotteryapp.fragment.RuneFragment;
import com.lotteryapp.view.TabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mVpMain;
    private TabView tab_menu1,tab_menu3,tab_menu4,tab_menu5;
    private LinearLayout drawBtn;
    private List<TabView> mTabs = new ArrayList<>();

    private RuneFragment runeFragment;
    private MineFragment mineFragment;
    private AttentionFragment attentionFragment;
    private NewsFragment newsFragment;
    private List<Fragment> mFragments = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVpMain = (ViewPager) findViewById(R.id.vp_main);

        //AI画规
        drawBtn = (LinearLayout) findViewById(R.id.tab_menu2);
        drawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });


        iniViews();
        initViewPageAdapter();
        initEvents();

        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    private void initEvents() {
        for (int i=0;i<mTabs.size();i++){
            TabView tabView = mTabs.get(i);
            final  int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mVpMain.setCurrentItem(finalI,false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void initViewPageAdapter() {
        mVpMain.setOffscreenPageLimit(4);

        mVpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0){
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);

                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void iniViews() {
        tab_menu1 = findViewById(R.id.tab_menu1);       //江湖
        tab_menu4 = findViewById(R.id.tab_menu4);       //关注
        tab_menu5 = findViewById(R.id.tab_menu5);       //消息
        tab_menu3 = findViewById(R.id.tab_menu3);       //我的

        tab_menu1.setIconAndText(R.mipmap.tab_runescape,R.mipmap.tab_runescape_select,"江湖");
        tab_menu4.setIconAndText(R.mipmap.tab_attention,R.mipmap.tab_attention_select,"关注");
        tab_menu5.setIconAndText(R.mipmap.tab_news,R.mipmap.tab_news_select,"消息");
        tab_menu3.setIconAndText(R.mipmap.tab_mine,R.mipmap.tab_mine_selected,"我的");

        mTabs.add(tab_menu1);
        mTabs.add(tab_menu4);
        mTabs.add(tab_menu5);
        mTabs.add(tab_menu3);
        setCurrentTab(0);

        runeFragment = new RuneFragment();
        attentionFragment = new AttentionFragment();
        newsFragment = new NewsFragment();
        mineFragment = new MineFragment();

        mFragments.add(runeFragment);
        mFragments.add(attentionFragment);
        mFragments.add(newsFragment);
        mFragments.add(mineFragment);
    }

    private void setCurrentTab(int pos){
        for (int i=0;i<mTabs.size();i++){
            TabView tabView = mTabs.get(i);
            if (i == pos){
                tabView.setProgress(1);
            }else{
                tabView.setProgress(0);
            }
        }
    }
}
