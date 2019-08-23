package com.lotteryapp.draw;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lotteryapp.R;

public class DrawDialog extends Dialog{
    private Context context;      //上下文
    private int layoutRes;        //布局文件

    public DrawDialog(Context context,int layoutRes){
        super(context, R.style.DialogTheme);  //加载dialog样式
        this.context = context;
        this.layoutRes = layoutRes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);  //设置dialog显示居中
        setContentView(layoutRes);

        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();

        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);   //点击外部Dialog消失
    }
}