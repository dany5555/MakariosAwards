package com.makariosawards;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class NomineesRecyclerviewAdapter extends RecyclerView.Adapter<NomineesRecyclerviewAdapter.ViewHolder> {

    private ArrayList<NomineesModel> nomineesModelArrayList;
    Context context;
    NomineesModel nomineesModel = new NomineesModel();

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView nomineePicture;
        TextView nomineeName;

        public ViewHolder(View itemView) {
            super(itemView);

            nomineePicture = itemView.findViewById(R.id.nominee_picture);
            nomineeName = itemView.findViewById(R.id.nominee_name);
        }
    }

    public NomineesRecyclerviewAdapter(Context context, ArrayList<NomineesModel> nomineesModelArrayList) {
        this.context = context;
        this.nomineesModelArrayList = nomineesModelArrayList;
    }

    public NomineesRecyclerviewAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nominees_grid_model, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NomineesRecyclerviewAdapter.ViewHolder viewHolder, final int i) {

        Typeface face = Typeface.createFromAsset(context.getAssets(), "optima_regular.ttf");


        final NomineesModel nomineesModel = nomineesModelArrayList.get(i);

        viewHolder.nomineeName.setTypeface(face);
        viewHolder.nomineeName.setText(nomineesModel.getFirstName());
        String nomineePictureUrl = nomineesModel.getPictureUrl();
        Glide.with(context).load(nomineePictureUrl).transition(withCrossFade()).into(viewHolder.nomineePicture);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Member: " + nomineesModel.getFirstName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BioActivity.class);

                intent.putExtra("pictureUrl", nomineesModel.getPictureUrl());
                intent.putExtra("fullName", nomineesModel.getFullName());
                intent.putExtra("age", nomineesModel.getAge());
                intent.putExtra("nationality", nomineesModel.getNationality());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nomineesModelArrayList.size();
    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<NomineesModel> nomineesModelArrayList) {
        nomineesModelArrayList.clear();
        nomineesModelArrayList.addAll(nomineesModelArrayList);
        notifyDataSetChanged();

    }
}
