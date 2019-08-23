package com.lotteryapp.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lotteryapp.MainActivity;
import com.lotteryapp.R;

public class MineRuneActivity extends Activity {
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_rune);

        back = (ImageView) findViewById(R.id.back_mine);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineRuneActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
