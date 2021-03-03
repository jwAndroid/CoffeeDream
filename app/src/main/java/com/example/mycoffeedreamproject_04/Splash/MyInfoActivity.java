package com.example.mycoffeedreamproject_04.Splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mycoffeedreamproject_04.Auth.KakaoLoginApi;
import com.example.mycoffeedreamproject_04.MainActivity;
import com.example.mycoffeedreamproject_04.R;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kyleduo.switchbutton.SwitchButton;

public class MyInfoActivity extends AppCompatActivity {

    MainActivity mainActivity = new MainActivity();

    private ImageView iv_myProfile;
    private TextView tv_myName , tv_myEmail , th_textView;
    private Button btn_logout , btn_login , btn_goMain;

    private String strNickname;
    private String strProfile;
    private String strEmail;

    public static SwitchButton th_button;
    LinearLayout info_layout ;

    public static Context context_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        hideActionBar();
        context_main = this;

        iv_myProfile = findViewById(R.id.iv_myProfile);
        tv_myName = findViewById(R.id.tv_myName);
        tv_myEmail = findViewById(R.id.tv_myEmail);
        btn_logout = findViewById(R.id.btn_logout);
        btn_login = findViewById(R.id.btn_login);
        btn_goMain = findViewById(R.id.btn_goMain);
        th_textView = findViewById(R.id.th_textView);
        th_button = findViewById(R.id.th_button);
        info_layout = findViewById(R.id.info_layout);


        SharedPreferences settings = getSharedPreferences("test", 0);
        boolean silent = settings.getBoolean("switchkey", false);
        th_button.setChecked(silent);

        if(silent){
            info_layout.setBackgroundResource(R.drawable.intro_r_bg_black);
        }else{
            info_layout.setBackgroundResource(R.drawable.intro_r_bg);
        }

        Intent intent = getIntent();
        strNickname = intent.getStringExtra("myName");
        strProfile = intent.getStringExtra("myProfile");
        strEmail = intent.getStringExtra("myEmail");

        tv_myName.setText(strNickname);
        tv_myEmail.setText(strEmail);
        Glide.with(this).load(strProfile)
                .override(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                .into(iv_myProfile);



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoActivity.this);
                builder.setTitle("CoffeeDream").setMessage("로그아웃 하시겠습니까?").setIcon(R.drawable.icon);

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(getApplicationContext(), "정상적으로 로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {

                            @Override
                            public void onCompleteLogout() {
                                Intent intent = new Intent(MyInfoActivity.this, KakaoLoginApi.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                                finish();
                            }
                        });
                    }
                });
                builder.setNeutralButton("아니요" , null);
                builder.create().show();
            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(strNickname != null){
                    Toast.makeText(getApplicationContext() , "이미"+" "+strNickname+"님 으로 로그인이 되어있습니다",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MyInfoActivity.this , KakaoLoginApi.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    finish();
                }
            }
        });

        btn_goMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MyInfoActivity.this , MainActivity.class);
                intent.putExtra("name" , strNickname);
                intent.putExtra("profile",strProfile);
                intent.putExtra("email", strEmail);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
                finish();
            }
        });


        th_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                Toast.makeText(getApplicationContext() , "재실행후 테마가 변경됩니다." , Toast.LENGTH_SHORT).show();

                if(isChecked){

                    info_layout.setBackgroundResource(R.drawable.intro_r_bg_black);

                }else{
                    info_layout.setBackgroundResource(R.drawable.intro_r_bg);
                }

                SharedPreferences settings = getSharedPreferences("test", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.apply();


            }
        });

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }


}