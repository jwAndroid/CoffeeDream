package com.example.mycoffeedreamproject_04;

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

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {


    private List<ItemModel> itemModelList;
    private List<ItemModel> itemModelListFilter;
    private Context context;
    MainActivity mainActivity = new MainActivity();

    public CustomAdapter(List<ItemModel> itemModelList, Context context) {
        this.itemModelList = itemModelList;
        this.itemModelListFilter = itemModelList;
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

        if (view1 == null){
            view1 = LayoutInflater.from(context).inflate(R.layout.low_item, parent , false);
        }

        ImageView imageName = view1.findViewById(R.id.imageName);
        TextView tvName = view1.findViewById(R.id.tVname);
//        TextView tvDesc = view1.findViewById(R.id.tVdesc);

        String name = itemModel.getName();
        int desc = itemModel.getDescription();
        final int image = itemModel.getImage();

        imageName.setImageResource(image);
        tvName.setText(name);
//        tvDesc.setText(desc);


        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Clicked item" , "===>"+itemModel.getName());
                context.startActivity(new Intent(context , ClickedItemActivity.class)
                        .putExtra("data" , itemModel));
            }
        });

        return view1;

    }//getView.....

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if (charSequence == null || charSequence.length() == 0){

                    filterResults.count = itemModelListFilter.size();
                    filterResults.values = itemModelListFilter;
                }else{
                    String searchStr = charSequence.toString().toLowerCase();

                    List<ItemModel> searchResult = new ArrayList<>();

                    for(ItemModel itemModel : itemModelListFilter) {
                        if (itemModel.getName().toLowerCase().contains(searchStr)){
                            searchResult.add(itemModel);
                        }
                    }

                    filterResults.count = searchResult.size();
                    filterResults.values = searchResult;

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                itemModelList = (List<ItemModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

        return filter;
    }
}
