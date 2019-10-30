package com.makariosawards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder> {

    Context context;
    ArrayList<VerticalModel> verticalModelArrayList;

    public VerticalAdapter(Context context, ArrayList<VerticalModel> verticalModelArrayList) {
        this.context = context;
        this.verticalModelArrayList = verticalModelArrayList;
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_vertical, parent, false);
        return new VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, int position) {
        final VerticalModel verticalModel = verticalModelArrayList.get(position);

        String title = "2019-2020 Members";
        ArrayList<HorizontalModel> horizontalModelArrayList = verticalModel.getHorizontalModelArrayList();

        holder.title.setText(title);
        HorizontalAdapter horizontalAdapter = new HorizontalAdapter(context, horizontalModelArrayList);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        holder.recyclerView.setAdapter(horizontalAdapter);

        /**holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, verticalModel.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return verticalModelArrayList.size();
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView title;
        //Button button;

        public VerticalViewHolder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recyclerview1);
            title = itemView.findViewById(R.id.title);
            //button = itemView.findViewById(R.id.button);

        }
    }

    // This method fixes the issue of having the listview scroll all the way to the top when DB is updated.
    public void refill(ArrayList<VerticalModel> verticalModelArrayList) {
        verticalModelArrayList.clear();
        verticalModelArrayList.addAll(verticalModelArrayList);
        notifyDataSetChanged();

    }


}
