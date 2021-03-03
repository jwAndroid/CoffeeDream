package com.example.mycoffeedreamproject_04.Splash;

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

import com.example.mycoffeedreamproject_04.MainActivity;
import com.example.mycoffeedreamproject_04.R;

public class Intro2 extends AppCompatActivity {

    private VideoView videoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);

        hideActionBar();

        /* // */
        LinearLayout intro2_image = findViewById(R.id.intro2_image);
        SharedPreferences settings = getSharedPreferences("test", 0);
        boolean silent = settings.getBoolean("switchkey", false);
        if(silent){
            intro2_image.setBackgroundResource(R.drawable.event_r_black);
        }else{
            intro2_image.setBackgroundResource(R.drawable.event_r);
        }


        /*MediaPlayer구현 >> 대략 3가지 필요
        * URI가 parse된 uri
        * 컨트롤러
        * 리스너*/

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


        Button btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToLoginActivity();
            }
        });


    }//........onCreate

    private void moveToLoginActivity(){

        /*Kakao auth SDK TEST >> KAKAO에서 제공하는 로그인부분인데 사용자의 정보를 카카오로부터 받아서 MyInfo쪽에 넣어주려고 했었음
        * 실제 단말기에서 카카오 설치후 진행하면 된다.
        * 현재는 테스트용도이기떄문에 Main으로 직행 */

//        Intent intent =  new Intent(Intro2.this ,  KakaoLoginApi.class);
        Intent intent =  new Intent(Intro2.this ,  MainActivity.class);
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