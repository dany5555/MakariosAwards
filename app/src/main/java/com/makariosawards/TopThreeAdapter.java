package com.makariosawards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class TopThreeAdapter extends BaseAdapter {

    Context context;
    ArrayList<TopThreeListModel> topThreeListModelArrayList;
    TopThreeListModel topThreeListModel;
    FirebaseDatabase database;
    DatabaseReference ref;

    public TopThreeAdapter(Context context, ArrayList<TopThreeListModel> nomineesModelArrayList) {
        this.context = context;
        this.topThreeListModelArrayList = nomineesModelArrayList;
    }

    @Override
    public int getCount() {
        return topThreeListModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return topThreeListModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_model, viewGroup, false);
        }



        ImageView nomineePicture = view.findViewById(R.id.nominee_picture);
        TextView nomineeName = view.findViewById(R.id.nominee_name);
        TextView nomineeVotes = view.findViewById(R.id.nominee_votes);


        topThreeListModel = (TopThreeListModel) this.getItem(i);


        nomineeName.setText(topThreeListModel.getFullName());
        nomineeVotes.setText("Votes: " + String.valueOf(topThreeListModel.getVotes()));


        //Picasso.get().load(matchesModel.getHomeTeamLogoUrl()).error(R.drawable.empty_team_logo).resize(50, 50).centerCrop().into(homeTeamLogo);
        //Picasso.get().load(matchesModel.getAwayTeamLogoUrl()).error(R.drawable.empty_team_logo).resize(50, 50).centerCrop().into(awayTeamLogo);
        Glide.with(context).load(topThreeListModel.getPictureUrl()).transition(withCrossFade()).into(nomineePicture);



        return view;

    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<TopThreeListModel> topThreeListModelArrayList) {
        topThreeListModelArrayList.clear();
        topThreeListModelArrayList.addAll(topThreeListModelArrayList);
        notifyDataSetChanged();

    }
}
