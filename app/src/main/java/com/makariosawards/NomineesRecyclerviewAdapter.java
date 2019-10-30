package com.makariosawards;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nominees_grid_model, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NomineesRecyclerviewAdapter.ViewHolder viewHolder, int i) {

        NomineesModel nomineesModel =nomineesModelArrayList.get(i);

        viewHolder.nomineeName.setText(nomineesModel.firstName);
        String nomineePictureUrl = nomineesModel.getPictureUrl();
        Glide.with(context).load(nomineePictureUrl).transition(withCrossFade()).into(viewHolder.nomineePicture);
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
