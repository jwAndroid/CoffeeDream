package com.example.mycoffeedreamproject_04.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycoffeedreamproject_04.R;

public class FragThursday extends Fragment {

    private View view;

    public Button fran_btn1,fran_btn2,
            fran_btn3,fran_btn4,
            fran_btn5,fran_btn6,
            fran_btn7,fran_btn8,fran_btn9;

    public ImageView main_img;



    public static FragThursday newInstance() {
        return new FragThursday();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_thursday, container, false);

        BtnOnClick btnOnClick = new BtnOnClick();

        fran_btn1 = view.findViewById(R.id.fran_btn1);  fran_btn1.setOnClickListener(btnOnClick);
        fran_btn2 = view.findViewById(R.id.fran_btn2);  fran_btn2.setOnClickListener(btnOnClick);
        fran_btn3 = view.findViewById(R.id.fran_btn3);  fran_btn3.setOnClickListener(btnOnClick);
        fran_btn4 = view.findViewById(R.id.fran_btn4);  fran_btn4.setOnClickListener(btnOnClick);
        fran_btn5 = view.findViewById(R.id.fran_btn5);  fran_btn5.setOnClickListener(btnOnClick);
        fran_btn6 = view.findViewById(R.id.fran_btn6);  fran_btn6.setOnClickListener(btnOnClick);
        fran_btn7 = view.findViewById(R.id.fran_btn7);  fran_btn7.setOnClickListener(btnOnClick);
        fran_btn8 = view.findViewById(R.id.fran_btn8);  fran_btn8.setOnClickListener(btnOnClick);
        fran_btn9 = view.findViewById(R.id.fran_btn9);  fran_btn9.setOnClickListener(btnOnClick);
        main_img = view.findViewById(R.id.main_img);

        return view;
    }



    /* 버튼이 많다보니까 밑 방식으로 진행*/
    class BtnOnClick implements View.OnClickListener{

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.fran_btn1 : main_img.setImageResource(R.drawable.main1);
                    break;

                case R.id.fran_btn2 : main_img.setImageResource(R.drawable.main2);
                    break;

                case R.id.fran_btn3 : main_img.setImageResource(R.drawable.main3);
                    break;

                case R.id.fran_btn4 : main_img.setImageResource(R.drawable.main4);
                    break;

                case R.id.fran_btn5 : main_img.setImageResource(R.drawable.main5);
                    break;

                case R.id.fran_btn6 : main_img.setImageResource(R.drawable.main6);
                    break;

                case R.id.fran_btn7 : main_img.setImageResource(R.drawable.main7);
                    break;

                case R.id.fran_btn8 : main_img.setImageResource(R.drawable.main8);
                    break;

                case R.id.fran_btn9 : main_img.setImageResource(R.drawable.main9);
                    break;



            }
        }
    }



}













