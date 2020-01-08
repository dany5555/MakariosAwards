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

public class AdminRecyclerViewAdapter extends RecyclerView.Adapter<AdminRecyclerViewAdapter.ViewHolder> {

    ArrayList<TopThreeListModel> topThreeListModelArrayList;
    Context context;
    TopThreeListModel topThreeListModel = new TopThreeListModel();

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView nomineePicture;
        TextView nomineeVotes, nomineeName;

        public ViewHolder(View itemView) {
            super(itemView);

            nomineePicture = itemView.findViewById(R.id.nominee_picture);
            nomineeName = itemView.findViewById(R.id.nominee_name);
            nomineeVotes = itemView.findViewById(R.id.nominee_votes);

        }
    }

    public AdminRecyclerViewAdapter(Context context, ArrayList<TopThreeListModel> topThreeListModelArrayList) {
        this.topThreeListModelArrayList = topThreeListModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRecyclerViewAdapter.ViewHolder holder, int position) {

        TopThreeListModel topThreeListModel = topThreeListModelArrayList.get(position);

        holder.nomineeName.setText(topThreeListModel.getFullName());

        holder.nomineeVotes.setText("Votes: " + String.valueOf(topThreeListModel.getVotes()));

        Glide.with(context).load(topThreeListModel.getPictureUrl()).transition(withCrossFade()).into(holder.nomineePicture);






    }

    @Override
    public int getItemCount() {
        return topThreeListModelArrayList.size();
    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<TopThreeListModel> topThreeListModelArrayList) {
        topThreeListModelArrayList.clear();
        topThreeListModelArrayList.addAll(topThreeListModelArrayList);
        notifyDataSetChanged();

    }
}
