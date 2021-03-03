package com.example.mycoffeedreamproject_04.Auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mycoffeedreamproject_04.MainActivity;
import com.example.mycoffeedreamproject_04.R;
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

/*GoogleApiClient.ConnectionCallbacks' is deprecated!!!
* 사용 x */

public class KakaoLoginApi extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private SessionCallback sessionCallback;
    private FirebaseAuth auth; // 인증변수
    private GoogleApiClient googleApiClient; // API 클라이언트 변수
    private static final int REQ_SIGN_GOOGLE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login_api);

        hideActionBar();

        /* // */
        ConstraintLayout intro2_image = findViewById(R.id.login_image);
        SharedPreferences settings = getSharedPreferences("test", 0);
        boolean silent = settings.getBoolean("switchkey", false);
        if(silent){
            intro2_image.setBackgroundResource(R.drawable.intro_r_bg_black);
        }else{
            intro2_image.setBackgroundResource(R.drawable.intro_r_bg);
        }

        /* 카카오로그인sdk를 이용하여 하려면 먼저 가장중요한 세션콜백객체가 필요
        * 두가지를 꼭 선언해주어야함.  */
        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
//        Session.getCurrentSession().checkAndImplicitOpen();


        /*버튼이벤트 => kakao login 세션 open */
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


        /*Firebase 에서 제공하는 구글auth 기본적인 세팅
        * 현재 연동해지해서 사용x
        * */
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        //토큰과 이메일을 가지고서 옵션생성

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions)
                .addConnectionCallbacks(this)
                .enableAutoManage(this,this)
                .build();
        //api 클라이언트 빌더를 startActivityForResult 로써 로직을 처리함.


        auth = FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화

        // implementation auth , google btn
        //버튼이벤트 >> googleApiClient를 던지고서 실행
        SignInButton btn_google = findViewById(R.id.btn_google);
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
            //REQ_SIGN_GOOGLE 구글로그인 정상결과 코드
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //넘겨받은 auth데이터를 가지고서 result객체생성

            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount(); // result.getSignInAccount(); 정보 모든걸 담고있음.
                assert account != null;
                resultLogin(account);
                //결과적으로 account를 가지고서 GoogleSignInAccount 파라메터로 던짐.
            }
        }
    }

    //최종적인 구글로그인 실행
    private void resultLogin(final GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken() , null);
        /* firebase는 이렇게 구글 어스 프로바이더로써 위 어카운트의 토큰을 가져다가 최종 크리덴셜 객체를 생성함
        * 결국 크리덴셜은 해당유저의 정보를 담고있으며 , 이 크리덴셜을 가지고서 구글 firebase 어스객체과 signInWithCredential 연동을 해주는것
        * */
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                /*정상진입이라면 */
                if(task.isSuccessful()){
                    Toast.makeText(KakaoLoginApi.this , "Google Login Successful" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                    /*유저정보는 account로 담겨있으니 데이터 빼와서 putExtra 해줄것 */
                    intent.putExtra("name" , account.getDisplayName());
                    intent.putExtra("profile" , String.valueOf(account.getPhotoUrl())); //string으로 변환해서 넘길것
                    intent.putExtra("email" , account.getEmail());
                    // 3가지 데이터를 intent , MainActivity쪽에서 테스트해보면 잘 나옴
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
            /* 세션의 상태에 대해서 핸들링 */
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    /*만약 있다면 특정 errorResult 를 가져다가 에러 제어*/
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
                    /*Response result값 > kakao에서 받은 유저데이터 반환
                    * deprecated */
                    Toast.makeText(getApplicationContext(),"Kakao Login Successful" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("name", result.getNickname()); //이름 보내고
                    intent.putExtra("profile", result.getProfileImagePath()); // 이미지 path 보내고 이부분은 추후에 글라이드나 피카소 사용해서 받아줄것

                    if(result.getKakaoAccount().hasEmail() == OptionalBoolean.TRUE){
                        //이메일이 있다면 현 유저의 이메일을 보내고
                        intent.putExtra("email",result.getKakaoAccount().getEmail());
                    }else{
                        //그렇지않으면 none 처리
                        intent.putExtra("email", "none");
                    }

                    //스타트 액티비티 해주면서 애니메이션 처리
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
                    finish();

                }
            });
        }

        //익셉션 처리
        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}