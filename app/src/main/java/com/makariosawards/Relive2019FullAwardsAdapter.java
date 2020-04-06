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

public class Relive2019FullAwardsAdapter extends RecyclerView.Adapter<Relive2019FullAwardsAdapter.ViewHolder> {

    ArrayList<Relive2019WinnersModel> fullList;
    Context context;

    public Relive2019FullAwardsAdapter(ArrayList<Relive2019WinnersModel> fullList, Context context) {
        this.fullList = fullList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Relive2019WinnersModel model = fullList.get(position);

        holder.fullName.setText(model.fullName);
        holder.votes.setText(String.valueOf(model.votes));
        Glide.with(context).load(model.pictureUrl).transition(withCrossFade()).into(holder.pictureImageView);


    }

    @Override
    public int getItemCount() {
        return fullList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pictureImageView;
        TextView fullName;
        TextView votes;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureImageView = itemView.findViewById(R.id.nominee_picture);
            fullName = itemView.findViewById(R.id.nominee_name);
            votes = itemView.findViewById(R.id.nominee_votes);
        }
    }
}
