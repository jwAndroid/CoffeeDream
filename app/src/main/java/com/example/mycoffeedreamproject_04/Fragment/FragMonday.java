package com.example.mycoffeedreamproject_04.Fragment;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycoffeedreamproject_04.R;

public class FragMonday extends Fragment {

    public VideoView videoview2;
    private Button btn_videoStop , btn_videoPlay;


    public static FragMonday newInstance() {
        return new FragMonday();
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

        //이부분은 test용
        //Uri uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        //원래는 parse의대한 getPackageName인데
        // Fragment에서는 getContext를 참조해서 연결시켜줌 or onAttach 할때(프래그먼트가 제일먼저 시작할떄) activity를 받아서 액티비티에 대한 패키지네임으로진행
        //videoview2.setMediaController(new MediaController(getContext()));

        Uri uri = Uri.parse("android.resource://"+ getContext().getPackageName()+"/"+R.raw.coffeedream);
        videoview2.setVideoURI(uri); /*parse된 uri를 가지고서 */
        videoview2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                /*비디오 플레이 준비된다면 클릭할떄 >> start  */
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

        return view;
    }

}


