package com.umeng.soexample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private int time=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.img);
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(
                img,
                "translationX",
                0,600
        );
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(
                img,
                "translationY",
                0,1050
        );
        animatorx.setDuration(5000);
        animatorY.setDuration(5000);
        AnimatorSet set = new AnimatorSet();
        set.play(animatorx).with(animatorY);
        set.start();
        handler.sendEmptyMessageDelayed(1,1000);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if (time>0){
                    time--;
                }else {
                    Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                    startActivity(intent);
                    finish();
                }
                handler.sendEmptyMessageDelayed(1,1000);

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
