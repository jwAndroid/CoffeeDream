package com.example.mycoffeedreamproject_04.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycoffeedreamproject_04.Grid.GridView;
import com.example.mycoffeedreamproject_04.R;

public class FragWednesday extends Fragment {

    private View view;

    private Button btn_coffee , btn_ade , btn_tea , btn_bakery , btn_season , btn_dessert,
                    btn_macaroon , btn_blende , btn_sandwich , btn_salad;

    private int number;

    public static FragWednesday newInstance() {
        FragWednesday fragWednesday = new FragWednesday();
        return fragWednesday;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_wednesday, container, false);

        btn_coffee = view.findViewById(R.id.btn_coffee);
        btn_ade = view.findViewById(R.id.btn_ade);
        btn_tea = view.findViewById(R.id.btn_tea);
        btn_bakery = view.findViewById(R.id.btn_bakery);
        btn_season = view.findViewById(R.id.btn_season);
        btn_dessert = view.findViewById(R.id.btn_dessert);
        btn_macaroon = view.findViewById(R.id.btn_macaroon);
        btn_blende = view.findViewById(R.id.btn_blende);
        btn_sandwich = view.findViewById(R.id.btn_sandwich);
        btn_salad = view.findViewById(R.id.btn_salad);


        btn_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //카테고리 구분을 위해 number 라는 구분코드 보냄.
                number = 1;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);

            }
        });

        btn_ade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 2;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);

            }
        });

        btn_tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 3;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);
            }
        });

        btn_bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 4;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);
            }
        });

        btn_season.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                number = 5;
                Intent intent = new Intent(getContext() , GridView.class);
                Log.e("btn_season","btn_season_TEST");
                intent.putExtra("number" , number );
                startActivity(intent);
            }
        });

        btn_dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 6;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);

            }
        });

        btn_macaroon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 7;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);

            }
        });

        btn_blende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 8;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);

            }
        });

        btn_sandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 9;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);
            }
        });

        btn_salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = 10;
                Intent intent = new Intent(getContext() , GridView.class);
                intent.putExtra("number" , number );
                startActivity(intent);
            }
        });


        return view;
    }
}
