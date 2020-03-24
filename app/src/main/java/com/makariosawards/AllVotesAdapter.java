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

public class AllVotesAdapter extends RecyclerView.Adapter {

    ArrayList<TopThreeListModel> topThreeListModelArrayList;
    Context context;

    public AllVotesAdapter(ArrayList<TopThreeListModel> topThreeListModelArrayList, Context context) {
        this.topThreeListModelArrayList = topThreeListModelArrayList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0 || position == 1 || position == 2) {
            return 0;
        }

        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;

        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.admin_category_recyclerview_item_2, parent, false);
            return new TopThreeViewHolder(view);
        }

        view = layoutInflater.inflate(R.layout.admin_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (position == 0 || position == 1 || position == 2) {
            TopThreeViewHolder topThreeViewHolder = (TopThreeViewHolder) holder;

            topThreeViewHolder.personName.setText(topThreeListModelArrayList.get(position).getFullName());
            topThreeViewHolder.votes.setText(String.valueOf(topThreeListModelArrayList.get(position).getVotes()));
            Glide.with(context).load(topThreeListModelArrayList.get(position).getPictureUrl()).transition(withCrossFade()).into(topThreeViewHolder.profilePicture);

            if (topThreeListModelArrayList.get(position).getDirectiva().equals("yes")) {
                topThreeViewHolder.directivaBadge.setVisibility(View.VISIBLE);
            } else if (topThreeListModelArrayList.get(position).getDirectiva().equals("no")){
                topThreeViewHolder.directivaBadge.setVisibility(View.GONE);
            }

            if (topThreeListModelArrayList.get(position).getDrama().equals("yes")) {
                topThreeViewHolder.dramaBadge.setVisibility(View.VISIBLE);
            } else {
                topThreeViewHolder.dramaBadge.setVisibility(View.GONE);
            }

            if (topThreeListModelArrayList.get(position).getMusic().equals("yes")) {
                topThreeViewHolder.musicBadge.setVisibility(View.VISIBLE);
            } else {
                topThreeViewHolder.musicBadge.setVisibility(View.GONE);
            }

            if (topThreeListModelArrayList.get(position).getPoetry().equals("yes")) {
                topThreeViewHolder.poetryBadge.setVisibility(View.VISIBLE);
            } else {
                topThreeViewHolder.poetryBadge.setVisibility(View.GONE);
            }
        } else {
            ListViewHolder listViewHolder = (ListViewHolder) holder;

            listViewHolder.personName.setText(topThreeListModelArrayList.get(position).getFullName());
            Glide.with(context).load(topThreeListModelArrayList.get(position).getPictureUrl()).transition(withCrossFade()).into(listViewHolder.profilePicture);
            listViewHolder.votes.setText(String.valueOf(topThreeListModelArrayList.get(position).getVotes()));
        }






    }

    @Override
    public int getItemCount() {
        return topThreeListModelArrayList.size();
    }

    class TopThreeViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePicture;
        TextView personName, votes;
        ImageView musicBadge, poetryBadge, dramaBadge, directivaBadge;

        public TopThreeViewHolder(View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.person_picture);
            personName = itemView.findViewById(R.id.person_name);
            votes = itemView.findViewById(R.id.votes_textview);
            musicBadge = itemView.findViewById(R.id.music_badge);
            dramaBadge = itemView.findViewById(R.id.drama_badge);
            poetryBadge = itemView.findViewById(R.id.poetry_badge);
            directivaBadge = itemView.findViewById(R.id.directiva_badge);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePicture;
        TextView personName, votes;

        public ListViewHolder(View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.nominee_picture);
            personName = itemView.findViewById(R.id.nominee_name);
            votes = itemView.findViewById(R.id.nominee_votes);
        }
    }

    public void refill(ArrayList<TopThreeListModel> topThreeListModelArrayList) {
        topThreeListModelArrayList.clear();
        topThreeListModelArrayList.addAll(topThreeListModelArrayList);
        notifyDataSetChanged();

    }
}
