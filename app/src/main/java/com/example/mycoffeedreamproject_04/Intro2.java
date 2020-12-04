package com.example.mycoffeedreamproject_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.ActionBar;

public class Intro2 extends AppCompatActivity {

    private Button btn_skip;
    private VideoView videoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);

        hideActionBar();

        LinearLayout intro2_image = findViewById(R.id.intro2_image);
        SharedPreferences settings = getSharedPreferences("test", 0);
        boolean silent = settings.getBoolean("switchkey", false);

        if(silent){
            intro2_image.setBackgroundResource(R.drawable.event_r_black);
        }else{
            intro2_image.setBackgroundResource(R.drawable.event_r);
        }

        btn_skip = findViewById(R.id.btn_skip);
        videoView1 = findViewById(R.id.videoView1);
        Uri videoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.event);
        videoView1.setMediaController(new MediaController(this));
        videoView1.setVideoURI(videoUri);
        videoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView1.start();
            }
        });



//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                try {
//                    Thread.sleep(17000);
//                    moveToLoginActivity();
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moveToLoginActivity();
            }
        });


    }//........onCreate

    private void moveToLoginActivity(){

        Intent intent =  new Intent(Intro2.this ,  KakaoLoginApi.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
        finish();

    }

    private void hideActionBar() {

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

}