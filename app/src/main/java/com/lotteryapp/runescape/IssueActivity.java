package com.lotteryapp.runescape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lotteryapp.MainActivity;
import com.lotteryapp.R;
import com.lotteryapp.draw.DrawDialog;
import com.lotteryapp.mine.RulesLibraryActivity;

public class IssueActivity extends Activity{
    private ImageView issueAdd;
    private ImageView back;
    private DrawDialog issueDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rune_issue_page);

        //添加画规
        issueAdd = (ImageView) findViewById(R.id.issue_add);
        issueAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                issueDialog = new DrawDialog(IssueActivity.this,R.layout.runescape_issue);
                issueDialog.show();
                issueDialog.getWindow().findViewById(R.id.issue_item2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(IssueActivity.this, RulesLibraryActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        //返回
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IssueActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //状态栏字体颜色
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
