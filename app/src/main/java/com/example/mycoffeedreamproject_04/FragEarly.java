package com.example.mycoffeedreamproject_04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragEarly extends Fragment {

    Button btn_map1 , btn_map2 , btn_map3 ,btn_map4 ,btn_map5;



    public static FragEarly newInstance() {

        FragEarly fragEarly = new FragEarly();
        return fragEarly;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_early, container, false);

//        EarlyBtnOnClick earlyBtnOnClick = new EarlyBtnOnClick(getContext());

        btn_map1 = view.findViewById(R.id.btn_map1);
        btn_map2 = view.findViewById(R.id.btn_map2);
        btn_map3 = view.findViewById(R.id.btn_map3);
        btn_map4 = view.findViewById(R.id.btn_map4);
        btn_map5 = view.findViewById(R.id.btn_map5);


        btn_map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext() , GoogleMap_1.class);
                startActivity(intent);
                Toast("GoogleMap_1");
            }
        });

        btn_map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext() , GoogleMap_2.class);
                startActivity(intent);
                Toast("GoogleMap_2");
            }
        });

        btn_map3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext() , GoogleMap_3.class);
                startActivity(intent);
                Toast("GoogleMap_3");
            }
        });

        btn_map4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext() , GoogleMap_4.class);
                startActivity(intent);
                Toast("GoogleMap_4");
            }
        });

        btn_map5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext() , GoogleMap_5.class);
                startActivity(intent);
                Toast("GoogleMap_5");
            }
        });

        return view;
    }

    public void Toast(String meg){
        Toast.makeText(getContext(),meg,Toast.LENGTH_SHORT).show();
    }



}


//없어도 됩니다 .. 
class EarlyBtnOnClick implements View.OnClickListener{


    Context context;

    public EarlyBtnOnClick(Context context){

        this.context = context;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_map1 : ThisToast("지도테스트1");
                break;

            case R.id.btn_map2 : ThisToast("지도테스트2");
                break;

            case R.id.btn_map3 : ThisToast("지도테스트3");
                break;

            case R.id.btn_map4 : ThisToast("지도테스트4");
                break;

            case R.id.btn_map5 : ThisToast("지도테스트5");
                break;

        }


    }

    public void ThisToast(String Message) {

        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();

    }

}
