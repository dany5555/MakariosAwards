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
        } view = layoutInflater.inflate(R.layout.countdown_view, parent, false);
        return new CountdownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        if (homeDataModelArrayList.get(position).viewType.equals("article")) {

            ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
            articleViewHolder.titleTextView.setText(homeDataModelArrayList.get(position).title);
            Glide.with(context).load(homeDataModelArrayList.get(position).primaryImageUrl).transition(withCrossFade()).into(articleViewHolder.primaryImageView);

            articleViewHolder.articleCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, homeDataModelArrayList.get(position).appLink, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ArticleActivity.class);
                    intent.putExtra("title", homeDataModelArrayList.get(position).title);
                    intent.putExtra("primaryImageUrl", homeDataModelArrayList.get(position).primaryImageUrl);
                    intent.putExtra("secondaryImageUrl", homeDataModelArrayList.get(position).secondaryImageUrl);
                    intent.putExtra("dateAndAuthor", homeDataModelArrayList.get(position).dateAndAuthor);
                    intent.putExtra("paragraph1", homeDataModelArrayList.get(position).paragraph1);
                    intent.putExtra("paragraph2", homeDataModelArrayList.get(position).paragraph2);
                    intent.putExtra("paragraph3", homeDataModelArrayList.get(position).paragraph3);
                    intent.putExtra("paragraph4", homeDataModelArrayList.get(position).paragraph4);
                    intent.putExtra("paragraph5", homeDataModelArrayList.get(position).paragraph5);
                    intent.putExtra("paragraph6", homeDataModelArrayList.get(position).paragraph6);
                    intent.putExtra("paragraph7", homeDataModelArrayList.get(position).paragraph7);
                    intent.putExtra("paragraph8", homeDataModelArrayList.get(position).paragraph8);
                    context.getApplicationContext().startActivity(intent);

                }
            });


        } else if (homeDataModelArrayList.get(position).viewType.equals("special")) {

            SpecialViewHolder specialViewHolder = (SpecialViewHolder) holder;
            specialViewHolder.specialButton.setText(homeDataModelArrayList.get(position).title);
            Glide.with(context).load(homeDataModelArrayList.get(position).primaryImageUrl).transition(withCrossFade()).into(specialViewHolder.primaryImageView);

            specialViewHolder.specialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, homeDataModelArrayList.get(position).appLink, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            CountdownViewHolder countdownViewHolder = (CountdownViewHolder) holder;
            countdownViewHolder.countdownTitle.setText(homeDataModelArrayList.get(position).title);
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

            countdownTitle = itemView.findViewById(R.id.countdown_title);
        }
    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<HomeDataModel> homeDataModelArrayList) {
        homeDataModelArrayList.clear();
        homeDataModelArrayList.addAll(homeDataModelArrayList);
        notifyDataSetChanged();

    }
}
