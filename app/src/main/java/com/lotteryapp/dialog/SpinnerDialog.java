package com.lotteryapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.lotteryapp.R;

public class SpinnerDialog extends Dialog {
    private Context context;      //上下文
    private int layoutRes;        //布局文件
    RelativeLayout relativeLayout;

    public SpinnerDialog(Context context,int layoutRes,Object obj){
        super(context, R.style.DialogTheme);  //加载dialog样式
        this.context = context;
        this.layoutRes = layoutRes;
        relativeLayout=(RelativeLayout)obj;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window dialogWindow = getWindow();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.BOTTOM);
        setContentView(layoutRes);

        setCanceledOnTouchOutside(true);   //点击外部Dialog消失
    }

    @Override
    protected void onStop() {
        super.onStop();
        relativeLayout.setVisibility(View.VISIBLE);
    }
}
