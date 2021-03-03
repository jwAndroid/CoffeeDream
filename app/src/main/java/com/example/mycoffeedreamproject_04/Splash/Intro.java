package com.example.mycoffeedreamproject_04.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;

import com.example.mycoffeedreamproject_04.R;

public class Intro extends AppCompatActivity {

    private static final String TAG = "Intro";
    Animation mAni1 , mAni2;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        hideActionBar();

        Button btnLogo = findViewById(R.id.btnLogo);
        Button btnLogo2 = findViewById(R.id.btnLogo2);
        LinearLayout intro_image = findViewById(R.id.intro_image);

        /*MyInfo 에서받은 boolena값을 받아서 setBackgroundResource 처리 */
        SharedPreferences settings = getSharedPreferences("test", 0);
        boolean silent = settings.getBoolean("switchkey", false);
        Log.d(TAG, "SharedPreferences 의 silent 값은? : " + silent);
        if(silent){
            intro_image.setBackgroundResource(R.drawable.intro_r_bg_black);
        }else{
            intro_image.setBackgroundResource(R.drawable.intro_r_bg);
        }

        /*애니메이션 처리 */
        mAni1 = AnimationUtils.loadAnimation(this,R.anim.rotate2);
        mAni2 = AnimationUtils.loadAnimation(Intro.this , R.anim.alpha);
        btnLogo.startAnimation(mAni1);
        btnLogo2.startAnimation(mAni2);
        //인트로화면 벨소리추가.
        player = MediaPlayer.create(Intro.this , R.raw.bells);
        player.start();

        /*3초 슬립후 moveToEvent()*/
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000); // 3초뒤에 반드시 main으로 넘어가는것을 동작하도록 스레드를 심어준다.
                    moveToEvent();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**/
    private void moveToEvent() {
        Intent intent = new Intent(Intro.this , Intro2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
        finish();
    }


    /*현재 Activity 에서만 actionBar.hide() 진행 */
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


}