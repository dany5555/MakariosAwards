package com.makariosawards;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HomeFragmentAdapter extends RecyclerView.Adapter {

    ArrayList<HomeDataModel> homeDataModelArrayList;
    Context context;

    public HomeFragmentAdapter(ArrayList<HomeDataModel> homeDataModelArrayList, Context context) {
        this.homeDataModelArrayList = homeDataModelArrayList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (homeDataModelArrayList.get(position).viewType.equals("article")) {
            return 0;
        } else if (homeDataModelArrayList.get(position).viewType.equals("special")) {
            return 1;
        } return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.article_view, parent, false);
            Log.e("lol", "This is a type: " + viewType);
            return new ArticleViewHolder(view);
        } else if (viewType == 1) {
            view = layoutInflater.inflate(R.layout.special_view, parent, false);
            return new SpecialViewHolder(view);
        } view = layoutInflater.inflate(R.layout.new_categories_list_item, parent, false);
        return new CountdownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        if (homeDataModelArrayList.get(position).viewType.equals("article")) {

            ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
            articleViewHolder.titleTextView.setText(homeDataModelArrayList.get(position).title);
            Glide.with(context).load(homeDataModelArrayList.get(position).getFirstImageUrl()).transition(withCrossFade()).into(articleViewHolder.primaryImageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, homeDataModelArrayList.get(position).getAppLink(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ArticleActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("title", homeDataModelArrayList.get(position).getTitle());
                    intent.putExtra("firstImageUrl", homeDataModelArrayList.get(position).getFirstImageUrl());
                    intent.putExtra("secondImageUrl", homeDataModelArrayList.get(position).getSecondImageUrl());
                    intent.putExtra("dateAndAuthor", homeDataModelArrayList.get(position).getDateAndAuthor());
                    intent.putExtra("firstParagraph", homeDataModelArrayList.get(position).getFirstParagraph());
                    intent.putExtra("secondParagraph", homeDataModelArrayList.get(position).getSecondParagraph());

                    context.getApplicationContext().startActivity(intent);

                }
            });


        } else if (homeDataModelArrayList.get(position).viewType.equals("special")) {

            SpecialViewHolder specialViewHolder = (SpecialViewHolder) holder;
            specialViewHolder.specialButton.setText(homeDataModelArrayList.get(position).title);
            Glide.with(context).load(homeDataModelArrayList.get(position).getFirstImageUrl()).transition(withCrossFade()).into(specialViewHolder.primaryImageView);

            specialViewHolder.specialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, homeDataModelArrayList.get(position).getAppLink(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Relive2019Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
        } else {
            CountdownViewHolder countdownViewHolder = (CountdownViewHolder) holder;
            countdownViewHolder.countdownTitle.setText(homeDataModelArrayList.get(position).getTitle());
        }



    }

    @Override
    public int getItemCount() {
        return homeDataModelArrayList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        ImageView primaryImageView;
        CardView articleCardView;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.article_title_textView);
            primaryImageView = itemView.findViewById(R.id.article_imageView);
            articleCardView = itemView.findViewById(R.id.article_cardView);
        }
    }

    class SpecialViewHolder extends RecyclerView.ViewHolder {

        ImageView primaryImageView;
        Button specialButton;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);

            primaryImageView = itemView.findViewById(R.id.special_imageView);
            specialButton = itemView.findViewById(R.id.special_button);

        }
    }

    class CountdownViewHolder extends RecyclerView.ViewHolder {

        TextView countdownTitle;

        public CountdownViewHolder(@NonNull View itemView) {
            super(itemView);

            countdownTitle = itemView.findViewById(R.id.category_textView);
        }
    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<HomeDataModel> homeDataModelArrayList) {
        homeDataModelArrayList.clear();
        homeDataModelArrayList.addAll(homeDataModelArrayList);
        notifyDataSetChanged();

    }
}
