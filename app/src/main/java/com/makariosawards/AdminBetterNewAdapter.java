package com.makariosawards;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminBetterNewAdapter extends RecyclerView.Adapter<AdminBetterNewAdapter.ViewHolder> {

    ArrayList<AdminMainModel> adminMainModelArrayList;
    Context context;

    public AdminBetterNewAdapter(ArrayList<AdminMainModel> adminMainModelArrayList, Context context) {
        this.adminMainModelArrayList = adminMainModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminBetterNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_categories_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AdminMainModel adminMainModel = adminMainModelArrayList.get(position);

        final String category = adminMainModel.getCategoryUid();

        holder.categoryTitle.setText(category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AllVotesActivity.class);
                intent.putExtra("category", category);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return adminMainModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;

        public ViewHolder (View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.category_textView);
        }
    }

    public void refill(ArrayList<AdminMainModel> adminMainModelArrayList) {
        adminMainModelArrayList.clear();
        adminMainModelArrayList.addAll(adminMainModelArrayList);
        notifyDataSetChanged();

    }
}
