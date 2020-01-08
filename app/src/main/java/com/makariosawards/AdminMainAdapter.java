package com.makariosawards;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class AdminMainAdapter extends RecyclerView.Adapter<AdminMainAdapter.ViewHolder> {

    ArrayList<AdminMainModel> adminMainModelArrayList;
    ArrayList<TopThreeListModel> lol = new ArrayList<>();
    Context context;
    AdminMainModel adminMainModel = new AdminMainModel();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categoryRef = database.getReference("Categories");

    public AdminMainAdapter(ArrayList<AdminMainModel> adminMainModelArrayList, Context context) {
        this.adminMainModelArrayList = adminMainModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_card_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        AdminMainModel adminMainModel = adminMainModelArrayList.get(position);

        final String currentCategory = adminMainModel.getCategoryUid();

        holder.categoryTitle.setText(currentCategory);

        // Do adapter stuff for the internal adapter. Going to use the TopThreeAdapter since it already
        // has what I need.
        //final ArrayList<TopThreeListModel> topThreeListModelArrayList = new ArrayList<>();
        AdminRecyclerViewAdapter adminRecyclerViewAdapter;

        categoryRef.child(currentCategory).child("Nominees").orderByChild("votes").limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lol.clear();

                if (holder.categoryRecyclerView.getAdapter() == null) {
                    AdminRecyclerViewAdapter adminRecyclerViewAdapter = new AdminRecyclerViewAdapter(context, lol);
                    holder.categoryRecyclerView.setAdapter(adminRecyclerViewAdapter);
                } else {
                    ((AdminRecyclerViewAdapter) holder.categoryRecyclerView.getAdapter()).refill(lol);
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    TopThreeListModel topThreeListModel = ds.getValue(TopThreeListModel.class);
                    lol.add(topThreeListModel);
                }

                ArrayList<TopThreeListModel> reversedList = lol;
                Collections.reverse(reversedList);
                lol = reversedList;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirects the admin to an activity with the full voting standings of the category.
                Intent intent = new Intent(context, AllVotesActivity.class);
                intent.putExtra("categoryTitle", currentCategory);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        // Set the adapter for the internal recycler view that holds the top three nominees.
        adminRecyclerViewAdapter = new AdminRecyclerViewAdapter(context, lol);
        holder.categoryRecyclerView.setNestedScrollingEnabled(false);
        holder.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.categoryRecyclerView.setAdapter(adminRecyclerViewAdapter);


    }

    @Override
    public int getItemCount() {
        return adminMainModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        Button moreButton;
        RecyclerView categoryRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            moreButton = itemView.findViewById(R.id.moreButton);
            categoryRecyclerView = itemView.findViewById(R.id.categoryRecyclerView);
        }
    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<AdminMainModel> adminMainModelArrayList) {
        adminMainModelArrayList.clear();
        adminMainModelArrayList.addAll(adminMainModelArrayList);
        notifyDataSetChanged();

    }




}
