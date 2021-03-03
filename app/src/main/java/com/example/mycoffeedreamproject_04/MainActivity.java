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
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mycoffeedreamproject_04.Splash.MyInfoActivity;
import com.google.android.material.tabs.TabLayout;
import com.kakao.plusfriend.PlusFriendService;
import com.kakao.util.exception.KakaoException;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "메인화면";
    /*퍼미션 REQUEST CODE */
    private final int PERMISSION_CALL_PHONE = 1001;

    /*BackPress event 전역변수 */
    private boolean isSendTelState = false;
    private long backBtnTime = 0;

    public FrameLayout layout;

    /*다이얼로그 전화번호 변수 리스트 생성 */
    private  final String telneumber1 = "01064278522";
    private  final String telneumber2 = "01012341234";
    private  final String telneumber3 = "01045674567";
    private  final String telneumber4 = "01043563456";
    private  final String telneumber5 = "01034567658";
    final String[] telnumbers = new String[]{"유성점", "대덕점", "서구점", "중구점", "동구점"};
    public static String strNickname, strProfile , email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideActionBar();

        // 뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.viewPager);
        /*ViewPagerAdapter 객체생성후에 viewPager를 셋팅후 진행  */

        FragmentPagerAdapter fragmentPagerAdapter
                = new ViewPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(fragmentPagerAdapter);
        /* newInstance()로써 반환된 fragment 객체를 셋팅해주는것 */
        tabLayout.setupWithViewPager(viewPager);

        Button btn_call = findViewById(R.id.btn_call);
        Button btn_email = findViewById(R.id.btn_email);
        Button btn_blog = findViewById(R.id.btn_blog);
        Button btn_faceBook = findViewById(R.id.btn_faceBook);
        Button btn_myinfo = findViewById(R.id.btn_myinfo);
        Button kakaoChatStart = findViewById(R.id.kakaoChatStart);

        /*auth 에서 데이터 getIntent */
        Intent intent = getIntent();
        strNickname = intent.getStringExtra("name");
        strProfile = intent.getStringExtra("profile"); // profile url
        email = intent.getStringExtra("email");
        Log.d(TAG, "User data : " + strNickname +"/n"+strProfile + "/n" + email);

        /*버튼이벤트 전화를 위한 다이얼로그 처리 tel()로써 기능 */
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Location")
                        .setIcon(R.drawable.icon)
                        .setNegativeButton("Cancel" , null) //취소버튼 null
                        .setItems(telnumbers, new DialogInterface.OnClickListener() { //위에 리스트 넣어서 진행
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {

                                switch (telnumbers[position]) {
                                    case "유성점":
                                        tel(telneumber1);
                                        break;
                                    case "대덕점":
                                        tel(telneumber2);
                                        break;
                                    case "서구점":
                                        tel(telneumber3);
                                        break;
                                    case "중구점":
                                        tel(telneumber4);
                                        break;
                                    case "동구점":
                                        tel(telneumber5);
                                        break;
                                }

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
                    /*카카오에서 제공하는 PlusFriendService 인스턴스와 ID값 을 넘겨서 진행한다.
                    * 아이디는 페이지 URL쪽에서 확인가능함. */
                    PlusFriendService.getInstance().chat(MainActivity.this, "_xixdVqK");
                } catch (KakaoException e) {

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        /* login 쪽에서 넘겨받은 데이터 */
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


    /**/
    public void onBackPressed() {

        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        /* curTime gapTime 두값을 이용해서 2초 이내로 백버튼 누를시에
        finish 대신 finishAffinity 로써 앱완전종료  */

        if(0 <= gapTime && 2000 >= gapTime){
            super.onBackPressed();
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

    /*tel() >> CALL_PHONE permission 먼저진행 isSendTelState 로써 로직을 나눠서 처리함.
     * 만약 받았으면 바로 Intent.ACTION_DIAL 로써 진행 */

    private void tel(String telNumber) {
        if(!isSendTelState){
            requestPermission2();
        }else{
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telNumber)));
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
        if (requestCode == PERMISSION_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isSendTelState = true;
                Log.d(TAG, "퍼미션체크1 동의 ");
                Toast("PERMISSION_GRANTED");
            } else {
                Log.d(TAG, "퍼미션체크2 거부");
                Toast("앱 동작시 전화연결을 위해 권한등록이 필요합니다.");
            }
        }
    }


    /*이메일*/
    public void emailSending(String email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        String address[] = {email};
        intent.putExtra(Intent.EXTRA_EMAIL , address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "test");
        startActivity(intent);
    }

    /*ACTION_VIEW 진행 */
    public void intentWeb(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(url));
        startActivity(intent);
    }


    /*토스트 간편하게 */
    public void Toast(String meg){
        Toast.makeText(this,meg,Toast.LENGTH_SHORT).show();
    }

}

