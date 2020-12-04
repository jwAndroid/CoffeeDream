package com.example.mycoffeedreamproject_04;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GridView extends AppCompatActivity {

    ImageFactory imageFactory = new ImageFactory();
    android.widget.GridView gridView;
    CustomAdapter customAdapter;
    private List<ItemModel> itemModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);


//        ActionBar ab = getActionBar();
//        ab.show();

        Intent intent = getIntent();
        int number = intent.getExtras().getInt("number");
        Log.e("TAG","number==>?"+number);

        if(number == 1 ){

            //i<imageFactory.image.length; 에 주의!
            for(int i = 0; i<imageFactory.image.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.image[i] , imageFactory.name[i] , imageFactory.description[i]);
                itemModelList.add(itemModel);
            }

        }
        if (number == 2){

            for(int i = 0; i<imageFactory.imageAde.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageAde[i] , imageFactory.nameAde[i] , imageFactory.descriptionAde[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 3){

            for(int i = 0; i<imageFactory.imageTea.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageTea[i] , imageFactory.nameTea[i] , imageFactory.descriptionTea[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 4){

            for(int i = 0; i<imageFactory.imageBakery.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageBakery[i] , imageFactory.nameBakery[i] , imageFactory.descriptionBakery[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 5){

            for(int i = 0; i<imageFactory.imageIcecream.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageIcecream[i] , imageFactory.nameIcecream[i] , imageFactory.descriptionIcecream[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 6){

            for(int i = 0; i<imageFactory.imageDejart.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageDejart[i] , imageFactory.nameDejart[i] , imageFactory.descriptionDejart[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 7){

            for(int i = 0; i<imageFactory.imageMacarron.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageMacarron[i] , imageFactory.nameMacarron[i] , imageFactory.descriptionMacarron[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 8){

            for(int i = 0; i<imageFactory.imageBlanded.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageBlanded[i] , imageFactory.nameBlanded[i] , imageFactory.descriptionBlanded[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 9){

            for(int i = 0; i<imageFactory.imageSandwich.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageSandwich[i] , imageFactory.nameSandwich[i] , imageFactory.descriptionSandwich[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        if (number == 10){

            for(int i = 0; i<imageFactory.imageSalad.length; i++){
                ItemModel itemModel = new ItemModel(imageFactory.imageSalad[i] , imageFactory.nameSalad[i] , imageFactory.descriptionSalad[i]);
                Log.e("TAG","itemModel");
                itemModelList.add(itemModel);
                Log.e("TAG","addList");
            }
        }

        gridView = findViewById(R.id.gridView);
        customAdapter = new CustomAdapter(itemModelList , this);
        gridView.setAdapter(customAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        ActionBar actionBar = getSupportActionBar();


        if(actionBar != null){

            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View actionbar = inflater.inflate(R.layout.custom_actionbar, null);
            actionBar.setCustomView(actionbar);
            Toolbar parent = (Toolbar)actionbar.getParent();
            parent.setContentInsetsAbsolute(0,0);

            ImageButton btnBack = findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onBackPressed();
//                    Intent intent = new Intent(getApplicationContext() , MainActivity.class); //로그인객체 사라짐.
//                    startActivity(intent);
                }
            });


            //actionBar.setTitle("CoffeeMenu");
            //이미 레이아웃상에서 정의해줌.
            //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.coffee_dream_main_r_01));
            //setBackgroundDrawable를 처리하고싶다면 사용할것.

        }

        getMenuInflater().inflate(R.menu.menu , menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("TAG" , "new text == > " + newText);
                customAdapter.getFilter().filter(newText);

                return true;
            }
        });

        return true;
    }



}