package com.example.mycoffeedreamproject_04.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycoffeedreamproject_04.R;

public class FragEnd extends Fragment {
//my info
    private View view;


    public static FragEnd newInstance() {
        FragEnd fragEnd = new FragEnd();
        return fragEnd;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragend, container, false);

        return view;

    }

}
