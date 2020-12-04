package com.example.mycoffeedreamproject_04;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;

public class KakaoLoginApi extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private SessionCallback sessionCallback;


    // implementation auth , google btn
    private SignInButton btn_google;
    private FirebaseAuth auth; // 인증변수
    private GoogleApiClient googleApiClient; // API 클라이언트 변수
    private static final int REQ_SIGN_GOOGLE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login_api);

        hideActionBar();

        ConstraintLayout intro2_image = findViewById(R.id.login_image);
        SharedPreferences settings = getSharedPreferences("test", 0);
        boolean silent = settings.getBoolean("switchkey", false);

        if(silent){
            intro2_image.setBackgroundResource(R.drawable.intro_r_bg_black);
        }else{
            intro2_image.setBackgroundResource(R.drawable.intro_r_bg);
        }

        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
//        Session.getCurrentSession().checkAndImplicitOpen();

        Button kakaoLoginButton2 = findViewById(R.id.kakaoLoginButton2);
        Log.e("TAG" , "findViewById");
        kakaoLoginButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG" , "setOnClickListener");
                try {
                    Session.getCurrentSession().open(AuthType.KAKAO_LOGIN_ALL, KakaoLoginApi.this);
                    Log.e("TAG" , "KAKAO_LOGIN_ALl");
                }catch (KakaoException e){
                    e.printStackTrace();
                    Log.e("TAG" , "printStackTrace");
                }

            }
        });


        //구글 연동을 위한 기본적인 객체생성과 셋팅
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .addConnectionCallbacks(this)
                .enableAutoManage(this,this)
                .build();

        auth = FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화

        btn_google = findViewById(R.id.btn_google);
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent , REQ_SIGN_GOOGLE);

            }
        });

    }

    private void hideActionBar() {

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);

            return;
        }


        if(requestCode == REQ_SIGN_GOOGLE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount(); // result.getSignInAccount(); 정보 모든걸 담고있음.
                resultLogin(account);
            }

        }

    }


    private void resultLogin(final GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken() , null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(KakaoLoginApi.this , "Google Login Successful" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                    intent.putExtra("name" , account.getDisplayName());
                    intent.putExtra("profile" , String.valueOf(account.getPhotoUrl()));
                    intent.putExtra("email" , account.getEmail());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
                    finish();

                }else{
                    Toast.makeText(KakaoLoginApi.this , "Loginfalse" , Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(),"세션이 닫혔습니다. 다시 시도해 주세요: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    Toast.makeText(getApplicationContext(),"Kakao Login Successful" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("name", result.getNickname());
                    intent.putExtra("profile", result.getProfileImagePath());

                    if(result.getKakaoAccount().hasEmail() == OptionalBoolean.TRUE){
                        intent.putExtra("email",result.getKakaoAccount().getEmail());
                    }else{
                        intent.putExtra("email", "none");
                    }

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
                    finish();

                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}