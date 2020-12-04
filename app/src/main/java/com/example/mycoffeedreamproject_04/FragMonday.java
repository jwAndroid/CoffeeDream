package com.example.mycoffeedreamproject_04;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import java.util.Objects;

public class FragMonday extends Fragment {

    public VideoView videoview2;
//  boolean isFull;
    private Button btn_videoStop , btn_videoPlay;


    public static FragMonday newInstance() {
        FragMonday fragMonday = new FragMonday();
        return fragMonday;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_monday, container, false);

        videoview2 = view.findViewById(R.id.videoview2);
        btn_videoStop = view.findViewById(R.id.btn_videoStop);
        btn_videoPlay = view.findViewById(R.id.btn_videoPlay);


        Button btn_menuCoffee = view.findViewById(R.id.btn_menuCoffee);
        Button btn_menuBakery = view.findViewById(R.id.btn_menuBakery);
        Button btn_menuTea = view.findViewById(R.id.btn_menuTea);
        Button btn_interior = view.findViewById(R.id.btn_interior);

        //이부분은 test용
        //Uri uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");

        Uri uri = Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.coffeedream);
        //원래는 parse의대한 getPackageName인데 onCreateView에서 getPackageName을 참조할수가 없나봄 .. 그래서 getContext를 참조해서 연결시켜줌
        //videoview2.setMediaController(new MediaController(getContext()));
        videoview2.setVideoURI(uri);
        videoview2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                btn_videoPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        videoview2.start();
                    }
                });

                btn_videoStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        videoview2.pause();
                    }
                });

            }
        });

//        btn_menuCoffee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext() , CoffeeGrid.class);
//                startActivity(intent);
//
//
//
//            }
//        });

//        btn_menuBakery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext() , BakeryGrid.class);
//                startActivity(intent);
//
//
//            }
//        });
//
//        btn_menuTea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext() , TeaGrid.class);
//                startActivity(intent);
//
//            }
//        });
//
//        btn_interior.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext() , InteriorGrid.class);
//                startActivity(intent);
//
//            }
//        });


        return view;
    }



}


