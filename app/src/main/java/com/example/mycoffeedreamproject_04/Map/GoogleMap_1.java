package com.example.mycoffeedreamproject_04.Map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mycoffeedreamproject_04.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/*21년부터 구글맵 사용법이 완전히 바뀌었다. 실제 사용을 위해 다시 등록하고 사용해야함.
* 코드 리펙토링 >> https://developers.google.com/maps/documentation/android-sdk/map?hl=ko */

public class GoogleMap_1 extends AppCompatActivity implements OnMapReadyCallback , GoogleMap.OnMarkerClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map_1);

        hideActionBar();
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng location = new LatLng(37.540721, 126.725979); //위치 찍기. (위치 검색해서 값 넣어주면 끝)
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("HOME");
        markerOptions.snippet("집");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location , 16));
        //줌을 어디에 몇배율로 셋팅할꺼냐.

        LatLng location2 = new LatLng(37.540039, 126.990809); //위치 찍기. (위치 검색해서 값 넣어주면 끝)
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.title("용산구");
        markerOptions2.snippet("용산구 이태원2동"); // 제목 아래에 표시되는 추가 텍스트.
        markerOptions2.position(location2);
        googleMap.addMarker(markerOptions2);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location , 16));
        //줌을 어디에 몇배율로 셋팅할꺼냐.


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(getApplicationContext() , marker.getTitle()
                        +marker.getPosition(),Toast.LENGTH_SHORT).show();

                if(marker.getTitle().equals("HOME")){
                    Toast.makeText(getApplicationContext() , "우리집!",Toast.LENGTH_SHORT).show();
                }
                else if(marker.getTitle().equals("용산구")){
                    Toast.makeText(getApplicationContext() , "용산구!",Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        // maker 여러개 생성
//        for (int idx = 0; idx < 10; idx++){
//            MarkerOptions makerOptions = new MarkerOptions();
//            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
//                    .position(new LatLng(37.52487 + idx, 126.92723))
//                    .title("마커" + idx); // 타이틀.
//
//            // 2. 마커 생성 (마커를 나타냄)
//            googleMap.addMarker(makerOptions);
//        }

    }


    private void hideActionBar() {

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}