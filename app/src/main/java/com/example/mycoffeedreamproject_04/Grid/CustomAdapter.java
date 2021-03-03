package com.example.mycoffeedreamproject_04.Grid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycoffeedreamproject_04.MainActivity;
import com.example.mycoffeedreamproject_04.Model.ItemModel;
import com.example.mycoffeedreamproject_04.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {

    /*리사이클러뷰 어댑터와 비슷한 역할을 한다. */

    private List<ItemModel> itemModelList;
    private List<ItemModel> itemModelListFilter;
    private Context context;

    public CustomAdapter(List<ItemModel> itemModelList, Context context) {
        this.itemModelList = itemModelList;
        this.itemModelListFilter = itemModelList; //검색구현을 위한 Filterable 인터페이스 진행하기위해서
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View view1 = view;

        final ItemModel itemModel = itemModelList.get(position);
        //add된 Model 객체 가져오기

        if (view1 == null){
            view1 = LayoutInflater.from(context).inflate(R.layout.low_item, parent , false);
            /* 만들어줄 각 아이템 레이아웃 Inflater */
        }

        /* low_item 값 할당 */
        ImageView imageName = view1.findViewById(R.id.imageName);
        TextView tvName = view1.findViewById(R.id.tVname);

        /* 각 포지션에 맞는 model 데이터 get */
        String name = itemModel.getName();
        int desc = itemModel.getDescription();
        final int image = itemModel.getImage();

        /* imageName 등록 / 이름 등록  */
        imageName.setImageResource(image);
        tvName.setText(name);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Clicked item" , "===>"+itemModel.getName());
                /* model 자체를 넘겨서 ClickedItemActivity 이쪽에서 처리 */
                context.startActivity(new Intent(context , ClickedItemActivity.class)
                        .putExtra("data" , itemModel));
            }
        });

        return view1;

    }//getView.....


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                //charSequence 아무것도 쓰지 않을떄
                if (charSequence == null || charSequence.length() == 0){

                    filterResults.count = itemModelListFilter.size();
                    filterResults.values = itemModelListFilter;

                    //본래 사이즈와 본래의 리스트를 넣어서 그리드뷰를 생성

                }else{
                    //무언가 써 내려갈떄

                    String searchStr = charSequence.toString().toLowerCase();
                    // charSequence 를 toString + 소문자변경 후 string data에 선언

                    //검색결과 리스트객체 생성
                    List<ItemModel> searchResult = new ArrayList<>();

                    //itemModelListFilter > 본래 리스트의 model데이터를 전부꺼내서 itemModel쪽에 넣고
                    for(ItemModel itemModel : itemModelListFilter) {
                        //itemModel의 getname으로써 진행하는데 유저의 charSequence가 만약에 contains 한다면
                        if (itemModel.getName().toLowerCase().contains(searchStr)){
                            //새로운 리스트에 해당 model을 담아주고
                            searchResult.add(itemModel);
                        }
                    }

                    //새로운 리스트를 넣어줄것
                    filterResults.count = searchResult.size();
                    filterResults.values = searchResult;

                }
                //두가지 경우중 하나를 반환
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                itemModelList = (List<ItemModel>) filterResults.values;
                //notify 등록
                notifyDataSetChanged();
            }
        };
    }
}
