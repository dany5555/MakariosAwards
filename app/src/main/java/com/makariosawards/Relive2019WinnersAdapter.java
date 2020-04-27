package com.makariosawards;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class Relive2019WinnersAdapter extends RecyclerView.Adapter<Relive2019WinnersAdapter.ViewHolder> {

    ArrayList<Relive2019WinnersModel> relive2019WinnersModelArrayList;
    ArrayList<Relive2019CategoriesModel> categoriesList;
    ArrayList<VideoLinksModel> videoLinksList;
    Context context;

    public Relive2019WinnersAdapter(ArrayList<Relive2019WinnersModel> relive2019WinnersModelArrayList, ArrayList<Relive2019CategoriesModel> categoriesList, ArrayList<VideoLinksModel> videoLinksList, Context context) {
        this.relive2019WinnersModelArrayList = relive2019WinnersModelArrayList;
        this.categoriesList = categoriesList;
        this.videoLinksList = videoLinksList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.relive_2019_awards_winner_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Relive2019WinnersModel model = relive2019WinnersModelArrayList.get(position);

        holder.winnerTextView.setText(model.fullName);
        holder.categoryTitle.setText(categoriesList.get(position).categoryName);
        Glide.with(context).load(model.pictureUrl).transition(withCrossFade()).into(holder.winnerImageView);

        holder.seeMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Relive2019FullAwardsActivity.class);
                intent.putExtra("category", categoriesList.get(position).categoryName);
                intent.putExtra("videoLink", videoLinksList.get(position).videoLink);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return relive2019WinnersModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView winnerImageView;
        TextView winnerTextView, categoryTitle;
        Button seeMoreButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            winnerImageView = itemView.findViewById(R.id.winnerImageView);
            winnerTextView = itemView.findViewById(R.id.winnerTextView);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            seeMoreButton = itemView.findViewById(R.id.seeMoreButton);
        }
    }
}
