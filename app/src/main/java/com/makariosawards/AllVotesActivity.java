package com.makariosawards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class AllVotesActivity extends AppCompatActivity {

    //Toolbar toolbar;
    TextView category;
    RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Categories");
    ArrayList<TopThreeListModel> topThreeListModelArrayList = new ArrayList<>();
    AllVotesAdapter allVotesAdapter;
    TopThreeListModel topThreeListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_votes);

        String categoryTitle = getIntent().getStringExtra("category");

        //toolbar = findViewById(R.id.all_votes_toolbar);
        category = findViewById(R.id.category_title);
        recyclerView = findViewById(R.id.all_votes_recyclerview);

        category.setText(categoryTitle);

        topThreeListModel = new TopThreeListModel();

        ref.child(categoryTitle).child("Nominees").orderByChild("votes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (recyclerView.getAdapter() == null) {
                    AllVotesAdapter allVotesAdapter = new AllVotesAdapter(topThreeListModelArrayList, getApplicationContext());
                    recyclerView.setAdapter(allVotesAdapter);
                } else {
                    ((AllVotesAdapter) recyclerView.getAdapter()).refill(topThreeListModelArrayList);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    topThreeListModel = ds.getValue(TopThreeListModel.class);
                    topThreeListModelArrayList.add(topThreeListModel);
                }

                ArrayList<TopThreeListModel> reversedList = topThreeListModelArrayList;
                Collections.reverse(reversedList);
                topThreeListModelArrayList = reversedList;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        allVotesAdapter = new AllVotesAdapter(topThreeListModelArrayList, getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(allVotesAdapter);

        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setTitle(categoryTitle);
        //getSupportActionBar().setLogo(R.drawable.ic_makarios_single_logo_for_app);


    }
}
