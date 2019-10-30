package com.makariosawards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {

    Context context;
    ArrayList<HorizontalModel> horizontalModelArrayList;

    public HorizontalAdapter(Context context, ArrayList<HorizontalModel> horizontalModelArrayList) {
        this.context = context;
        this.horizontalModelArrayList = horizontalModelArrayList;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {

        final HorizontalModel horizontalModel = horizontalModelArrayList.get(position);

        String url = horizontalModel.getPictureUrl();
        holder.title.setText(horizontalModel.getFirstName());
        Glide.with(context).load(url).transition(withCrossFade()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, horizontalModel.getFirstName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return horizontalModelArrayList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;

        public HorizontalViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textTitle);
        }
    }
}
