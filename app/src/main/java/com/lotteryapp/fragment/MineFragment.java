package com.lotteryapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lotteryapp.R;
import com.lotteryapp.mine.BillActivity;
import com.lotteryapp.mine.FeedBackActivity;
import com.lotteryapp.mine.FriendsActivity;
import com.lotteryapp.mine.MineRuneActivity;
import com.lotteryapp.mine.RechargeActivity;
import com.lotteryapp.mine.RulesLibraryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.util.Properties;

public class MineFragment extends Fragment {
    private LinearLayout loginWrp;
    private LinearLayout loginBtn;
    private LinearLayout mineMsgWrp;
    private LinearLayout mineFriends;

    private LinearLayout rulesLibrary;
    private TextView rechargeBtn;
    private LinearLayout bill;
    private LinearLayout feedback;
    private LinearLayout mineRune;
    public IWXAPI api;
    private Properties prop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_mine,container,false);

        loginBtn = (LinearLayout) view.findViewById(R.id.login_btn);
        loginWrp = (LinearLayout) view.findViewById(R.id.login_wrp);
        mineMsgWrp = (LinearLayout) view.findViewById(R.id.mine_msg_wrp);


        SharedPreferences pfs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        editor.putString("isLogin","1");
        editor.apply();
        String isLogin=pfs.getString("isLogin",null);
        if(isLogin==null||"0".equals(isLogin)){
            Log.d("TAG" ,"in if onCreateView: ");
            loginWrp.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.VISIBLE);
            mineMsgWrp.setVisibility(View.GONE);
        }

        //微信api注册
        prop=new Properties();
        try {
            prop.load(getContext().getAssets().open("wxconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        api= WXAPIFactory.createWXAPI(getContext(),prop.getProperty("appid"),true);
        api.registerApp(prop.getProperty("appid"));
        //登录按钮
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* loginWrp.setVisibility(View.GONE);
                mineMsgWrp.setVisibility(View.VISIBLE);*/
                if (api.isWXAppInstalled()) {  // 用户是否安装微信客户端
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "login";
                    api.sendReq(req);
                }else{
                    Toast.makeText(getContext(), "请安装微信客户端", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //规库
        rulesLibrary = (LinearLayout) view.findViewById(R.id.rules_library);
        rulesLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineFragment.this.getContext(), RulesLibraryActivity.class);
                startActivity(intent);
            }
        });

        //账单
        bill = (LinearLayout) view.findViewById(R.id.mine_bill);
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineFragment.this.getContext(), BillActivity.class);
                startActivity(intent);
            }
        });

        //充值
        rechargeBtn = (TextView) view.findViewById(R.id.mine_recharge);
        rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineFragment.this.getContext(), RechargeActivity.class);
                startActivity(intent);
            }
        });

        //有奖反馈
        feedback = (LinearLayout) view.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineFragment.this.getContext(), FeedBackActivity.class);
                startActivity(intent);
            }
        });

        //我的好友
        mineFriends = (LinearLayout) view.findViewById(R.id.mine_friends);
        mineFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineFragment.this.getContext(), FriendsActivity.class);
                startActivity(intent);
            }
        });

        //我的江湖
        mineRune = (LinearLayout) view.findViewById(R.id.mine_rune);
        mineRune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineFragment.this.getContext(), MineRuneActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }




}
