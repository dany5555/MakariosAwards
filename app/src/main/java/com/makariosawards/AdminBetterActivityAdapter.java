package com.makariosawards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminBetterActivityAdapter extends RecyclerView.Adapter<AdminBetterActivityAdapter.ViewHolder> {

    ArrayList<AdminMainModel> adminMainModelArrayList;
    Context context;
    OnCategoryListener mOnCategoryListener;

    public AdminBetterActivityAdapter(ArrayList<AdminMainModel> adminMainModelArrayList, Context context, OnCategoryListener onCategoryListener) {
        this.adminMainModelArrayList = adminMainModelArrayList;
        this.context = context;
        this.mOnCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public AdminBetterActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_categories_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view, mOnCategoryListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdminMainModel adminMainModel = adminMainModelArrayList.get(position);

        holder.categoryTitle.setText(adminMainModel.getCategoryUid());

    }

    @Override
    public int getItemCount() {
        return adminMainModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView categoryTitle;
        OnCategoryListener onCategoryListener;

        public ViewHolder (View itemView, OnCategoryListener onCategoryListener) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.category_textView);
            this.onCategoryListener = onCategoryListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCategoryListener.onCategoryClicked(getAdapterPosition());

        }
    }

    public void refill(ArrayList<AdminMainModel> adminMainModelArrayList) {
        adminMainModelArrayList.clear();
        adminMainModelArrayList.addAll(adminMainModelArrayList);
        notifyDataSetChanged();

    }

    public interface OnCategoryListener {
        void onCategoryClicked(int position);
    }






}
