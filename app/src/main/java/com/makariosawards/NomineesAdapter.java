package com.makariosawards;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NomineesAdapter extends BaseAdapter {

    Context context;
    ArrayList<NomineesModel> nomineesModelArrayList;
    NomineesModel nomineesModel;

    public NomineesAdapter(Context context, ArrayList<NomineesModel> nomineesModelArrayList) {
        this.context = context;
        this.nomineesModelArrayList = nomineesModelArrayList;
    }

    @Override
    public int getCount() {
        return nomineesModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return nomineesModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.nominees_grid_model, viewGroup, false);
        }

        ImageView nomineePicture = view.findViewById(R.id.nominee_picture);
        TextView nomineeName = view.findViewById(R.id.nominee_name);

        Typeface face = Typeface.createFromAsset(context.getAssets(), "optima_regular.ttf");

        nomineesModel = (NomineesModel) this.getItem(i);

        String nomineePictureUrl = nomineesModel.getPictureUrl();

        nomineeName.setTypeface(face);
        nomineeName.setText(nomineesModel.getFirstName());
        Glide.with(context).load(nomineePictureUrl).into(nomineePicture);


        return view;

}

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<NomineesModel> nomineesModelArrayList) {
        nomineesModelArrayList.clear();
        nomineesModelArrayList.addAll(nomineesModelArrayList);
        notifyDataSetChanged();

    }}
