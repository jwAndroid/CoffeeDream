package com.example.mycoffeedreamproject_04;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class
ClickedItemActivity extends AppCompatActivity {

    private ItemModel itemModel;
    private ImageView imageView , iv_des;
    private TextView tvName;
    private Button btn_back , btn_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_item);



        hideActionBar();
        imageView = findViewById(R.id.imageView);
        tvName = findViewById(R.id.tvName);
        iv_des = findViewById(R.id.iv_des);
        btn_back = findViewById(R.id.btn_back);
        btn_test = findViewById(R.id.btn_test);

        Intent intent = getIntent();

        if(intent.getExtras() != null){

            itemModel = (ItemModel) intent.getSerializableExtra("data");
            assert itemModel != null;
            Log.e("PASSED" , "===>"+itemModel.getName());

            final int image = itemModel.getImage();
            String name = itemModel.getName();
            int des = itemModel.getDescription();

//            int description = itemModel.getDescription();
            //이친구를 그리드뷰에 장착시키는게 아니고 , 이쪽 클래스에서 imageView data 뿌려주기.
            //이미지 imageFactory에서 미리 사진을 저장해놓고 이용

            imageView.setImageResource(image);
            iv_des.setImageResource(des);
            tvName.setText(name);

            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onBackPressed();
                    finish();
                }
            });

            btn_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(getApplicationContext() , "TestPage Intent" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext() , ContentsInfo.class);
                    intent.putExtra("data" , itemModel );
                    startActivity(intent);
                    finish();

                }
            });




        }

    }

    private void hideActionBar() {

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }
}