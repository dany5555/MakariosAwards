package com.makariosawards;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesAdapter extends BaseAdapter {

    Context context;
    ArrayList<CategoriesModel> categoriesModelArrayList;
    CategoriesModel categoriesModel;

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> categoriesModelArrayList) {
        this.categoriesModelArrayList = categoriesModelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoriesModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoriesModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.categories_list_model, viewGroup, false);
        }

        TextView categoryName = view.findViewById(R.id.category_title);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "optima_regular.ttf");

        categoriesModel = (CategoriesModel) this.getItem(i);

        categoryName.setTypeface(face);
        categoryName.setText(categoriesModel.getCategoryUid());

        return view;
    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<CategoriesModel> categoriesModelArrayList) {
        categoriesModelArrayList.clear();
        categoriesModelArrayList.addAll(categoriesModelArrayList);
        notifyDataSetChanged();

    }
}
