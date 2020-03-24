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

public class CastVoteListAdapter extends RecyclerView.Adapter<CastVoteListAdapter.ViewHolder> {

    ArrayList<NomineesModel> nomineesModelArrayList;
    Context context;
    OnNomineeListener onNomineeListener;
    String currentVote, checkVoting;

    public CastVoteListAdapter(ArrayList<NomineesModel> nomineesModelArrayList, Context context, String currentVote, String checkVoting, OnNomineeListener onNomineeListener) {
        this.nomineesModelArrayList = nomineesModelArrayList;
        this.context = context;
        this.onNomineeListener = onNomineeListener;
        this.currentVote = currentVote;
        this.checkVoting = checkVoting;
    }

    @NonNull
    @Override
    public CastVoteListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.vote_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view, onNomineeListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NomineesModel nomineesModel = nomineesModelArrayList.get(position);

        holder.nomineeName.setText(nomineesModel.getFirstName());
        Glide.with(context).load(nomineesModel.getPictureUrl()).transition(withCrossFade()).into(holder.nomineePicture);




    }

    @Override
    public int getItemCount() {
        return nomineesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nomineeName;
        ImageView nomineePicture;
        OnNomineeListener onNomineeListener;

        public ViewHolder (View itemView, OnNomineeListener onNomineeListener) {
            super(itemView);

            nomineeName = itemView.findViewById(R.id.nominee_name);
            nomineePicture = itemView.findViewById(R.id.nominee_picture);

            this.onNomineeListener = onNomineeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNomineeListener.onNomineeClicked(getAdapterPosition());
        }
    }

    public void refill(ArrayList<NomineesModel> nomineesModelArrayList) {
        nomineesModelArrayList.clear();
        nomineesModelArrayList.addAll(nomineesModelArrayList);
        notifyDataSetChanged();

    }

    public interface OnNomineeListener {
        void onNomineeClicked(int position);
    }
}
