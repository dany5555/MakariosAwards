package com.makariosawards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

class Relive2019NomineesAdapter extends RecyclerView.Adapter<Relive2019NomineesAdapter.ViewHolder> {

    ArrayList<Relive2019NomineesModel> relive2019NomineesModelArrayList;
    Context context;

    public Relive2019NomineesAdapter(ArrayList<Relive2019NomineesModel> relive2019NomineesModelArrayList, Context context) {
        this.relive2019NomineesModelArrayList = relive2019NomineesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nominees_grid_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Relive2019NomineesModel model = relive2019NomineesModelArrayList.get(position);

        holder.nomineeName.setText(model.firstname);
        Glide.with(context).load(model.pictureUrl).transition(withCrossFade()).into(holder.nomineePicture);


    }

    @Override
    public int getItemCount() {
        return relive2019NomineesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView nomineePicture;
        TextView nomineeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomineePicture = itemView.findViewById(R.id.nominee_picture);
            nomineeName = itemView.findViewById(R.id.nominee_name);


        }

    }


}
