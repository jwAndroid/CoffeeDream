package com.example.mycoffeedreamproject_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;

public class Intro extends AppCompatActivity {


    private Button btnLogo , btnLogo2;
    Animation mAni1 , mAni2;
    MediaPlayer player;

    MyInfoActivity myInfoActivity = new MyInfoActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        hideActionBar();

        btnLogo = findViewById(R.id.btnLogo);
        btnLogo2 = findViewById(R.id.btnLogo2);


        LinearLayout intro_image = findViewById(R.id.intro_image);
        SharedPreferences settings = getSharedPreferences("test", 0);
        boolean silent = settings.getBoolean("switchkey", false);

        if(silent){
            intro_image.setBackgroundResource(R.drawable.intro_r_bg_black);
        }else{
            intro_image.setBackgroundResource(R.drawable.intro_r_bg);
        }



        mAni1 = AnimationUtils.loadAnimation(this,R.anim.rotate2);
        mAni2 = AnimationUtils.loadAnimation(Intro.this , R.anim.alpha);

        btnLogo.startAnimation(mAni1);
        btnLogo2.startAnimation(mAni2);

        //인트로화면 벨소리추가.
        player = MediaPlayer.create(Intro.this , R.raw.bells);
        player.start();

        new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    Thread.sleep(3500); // 3초뒤에 반드시 main으로 넘어가는것을 동작하도록 스레드를 심어준다.
                    moveToEvent();
                    // moveToMain();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start(); // 스레드를 썻다면 반드시 start()를 호출할것.


    }

    private void moveToEvent() {

        Intent intent = new Intent(Intro.this , Intro2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
        finish();  // 호출후 , 현재 액티비티를 종료! 중요함.

    }


    private void hideActionBar() {

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }


}