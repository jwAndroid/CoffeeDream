package com.example.mycoffeedreamproject_04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.kakao.plusfriend.PlusFriendService;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private String TAG = "메인화면";
    private final int PERMISSION_CALL_PHONE = 1001;
    private boolean isSendTelState = false;
    private Button btn_call , btn_email , btn_blog , btn_faceBook , kakaoChatStart,
                    btn_myinfo;

    private long backBtnTime = 0;

//    private ImageView iv_profile;
//    private TextView tvNickname;

    private  final String telneumber1 = "01064278522";
    private  final String telneumber2 = "01012341234";
    private  final String telneumber3 = "01045674567";
    private  final String telneumber4 = "01043563456";
    private  final String telneumber5 = "01034567658";

    final String[] telnumbers = new String[]{"유성점", "대덕점", "서구점", "중구점", "동구점"};
    public static String strNickname, strProfile , email;
    public FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideActionBar();

        // 뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.viewPager);
        fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        btn_call = findViewById(R.id.btn_call);
        btn_email = findViewById(R.id.btn_email);
        btn_blog = findViewById(R.id.btn_blog);
        btn_faceBook = findViewById(R.id.btn_faceBook);
        btn_myinfo = findViewById(R.id.btn_myinfo);
        kakaoChatStart = findViewById(R.id.kakaoChatStart);


        Intent intent = getIntent();
        strNickname = intent.getStringExtra("name");
        strProfile = intent.getStringExtra("profile"); // profile url
        email = intent.getStringExtra("email");

//        Glide.with(this).load(strProfile).override(60,60).into(iv_profile);


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Location").setIcon(R.drawable.icon).setNegativeButton("Cancel" , null)
                        .setItems(telnumbers, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {

                                if(telnumbers[position].equals("유성점")){ tel(telneumber1);}

                                else if(telnumbers[position].equals("대덕점")){ tel(telneumber2);}

                                else if(telnumbers[position].equals("서구점")){ tel(telneumber3);}

                                else if(telnumbers[position].equals("중구점")){  tel(telneumber4);}

                                else if(telnumbers[position].equals("동구점")){  tel(telneumber5);}


                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailSending("cjd9408er@naver.com");
            }
        });

        btn_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentWeb("https://mygameprogamming.tistory.com/64");
            }
        });

        btn_faceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentWeb("https://www.youtube.com/?gl=KR&hl=ko");
            }
        });

        kakaoChatStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    PlusFriendService.getInstance().chat(MainActivity.this, "_xixdVqK");

                } catch (KakaoException e) {

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        btn_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , MyInfoActivity.class);
                intent.putExtra("myName" , strNickname);
                intent.putExtra("myProfile",strProfile);
                intent.putExtra("myEmail",email);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
                finish();
            }
        });

    }//.....onCreate..........................


    public void onBackPressed() {

        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime){
            super.onBackPressed();
            //finish();
            finishAffinity();
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(getApplicationContext() , "뒤로가기 두번눌러 뒤로가실수 있습니다." ,Toast.LENGTH_SHORT).show();
        }

    }


    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }


    private void tel(String telneumber) {

        if(isSendTelState == false ){
            requestPermission2();
            Log.d(TAG, "전화 통화를 위해 동의가 진행." );
        }else{
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telneumber)));
        }
    }

    private void requestPermission2() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},
                PERMISSION_CALL_PHONE);
                Log.d(TAG, "퍼미션체크 최초체크:CALL_PHONE" );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case PERMISSION_CALL_PHONE:

                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    isSendTelState=true;
                    Log.d(TAG, "퍼미션체크1 동의 " );
                    Toast("PERMISSION_GRANTED");
                }else{
                    Log.d(TAG, "퍼미션체크2 거부" );
                    Toast("앱 동작시 전화연결을 위해 권한등록이 필요합니다.");
                }
                break;

        }
    }


    public void emailSending(String email){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        String address[] = {email};
        intent.putExtra(Intent.EXTRA_EMAIL , address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "test");

        startActivity(intent);
    }

    public void intentWeb(String url){

        Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(url));

        startActivity(intent);
    }

    public void Toast(String meg){

        Toast.makeText(this,meg,Toast.LENGTH_SHORT).show();

    }


//    public void setFullScreen(boolean full) {
//
//
//        ViewGroup.LayoutParams params = layout.getLayoutParams();
//        isFull = full;
//
//        if (isFull) {
//
//            isFull = true;
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        } else {
//
//            isFull = false;
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            DisplayMetrics metrics = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(metrics);
//            int height = (int) (metrics.density * 250);
//            params.height = height;
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        }
//
//    }//..setFullScreen...

//    private void webView(String url){
//
//        web_view = findViewById(R.id.web_view);
//
//        web_view.getSettings().setJavaScriptEnabled(true);
//        web_view.loadUrl(url);
//        web_view.setWebChromeClient(new WebChromeClient());
//        web_view.setWebViewClient(new WebViewClientClass());
//    }


}

