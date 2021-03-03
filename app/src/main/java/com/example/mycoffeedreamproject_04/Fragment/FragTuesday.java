package com.example.mycoffeedreamproject_04.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycoffeedreamproject_04.Default.BrandStrory;
import com.example.mycoffeedreamproject_04.Default.CoffeeStory;
import com.example.mycoffeedreamproject_04.R;
import com.example.mycoffeedreamproject_04.Default.RoastingStory;

public class FragTuesday extends Fragment {



    public static FragTuesday newInstance() {
        FragTuesday fragTuesday = new FragTuesday();
        return fragTuesday;
    }


    @SuppressLint("CutPasteId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_tuesday, container, false);

        Button btn_story_1 = view.findViewById(R.id.btn_story_1);
        Button btn_story_2 = view.findViewById(R.id.btn_story_2);
        Button btn_story_3 = view.findViewById(R.id.btn_story_3);


        btn_story_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , BrandStrory.class);
                startActivity(intent);
            }
        });

        btn_story_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , CoffeeStory.class);
                startActivity(intent);
            }
        });

        btn_story_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , RoastingStory.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
