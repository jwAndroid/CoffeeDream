package com.example.mycoffeedreamproject_04;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ContentsInfo extends AppCompatActivity {


    private TextView tvName;
    private ItemModel itemModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_info);

        hideActionBar();

        tvName = findViewById(R.id.tvName);

        Intent intent = getIntent();

        if (intent != null){

            itemModel = (ItemModel) intent.getSerializableExtra("data");

            assert itemModel != null;
            Log.e("PASSED" , "===>"+itemModel.getName());
            String name = itemModel.getName();

//            imageView.setImageResource(image);
//            iv_des.setImageResource(des);
            //객체 정보를 가져올수있도록 테스트. 필요하면 해당객체의정보를 언제든 가져다 쓸수있도록 해놓음.

            tvName.setText(name);


        }





    }

    private void hideActionBar() {

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }
}