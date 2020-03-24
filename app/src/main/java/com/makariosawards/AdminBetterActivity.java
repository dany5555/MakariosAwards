package com.makariosawards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminBetterActivity extends AppCompatActivity implements AdminBetterActivityAdapter.OnCategoryListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categoryRef = database.getReference("Categories");
    AdminBetterActivityAdapter adminBetterActivityAdapter;
    ArrayList<AdminMainModel> adminMainModelArrayList = new ArrayList<>();
    AdminMainModel adminMainModel;

    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_better);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);

        adminMainModel =  new AdminMainModel();

        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*if (recyclerView.getAdapter() == null) {
                    //AdminBetterActivityAdapter adminBetterActivityAdapter = new AdminBetterActivityAdapter(adminMainModelArrayList, getApplicationContext(), AdminBetterActivityAdapter.OnCategoryListener);
                    recyclerView.setAdapter(adminBetterActivityAdapter);
                } else {
                    ((AdminBetterActivityAdapter) recyclerView.getAdapter()).refill(adminMainModelArrayList);
                }*/

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    adminMainModel = ds.getValue(AdminMainModel.class);

                    adminMainModelArrayList.add(adminMainModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adminBetterActivityAdapter = new AdminBetterActivityAdapter(adminMainModelArrayList, getApplicationContext(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adminBetterActivityAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_makarios_awards_logo_with_insignia_horizontal_taller);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);





    }

    @Override
    public void onCategoryClicked(int position) {

        String categoryTitle = adminMainModelArrayList.get(position).getCategoryUid();
        Toast.makeText(this, categoryTitle, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, FullVotingStandingsActivity.class);
        intent.putExtra("categoryTitle", categoryTitle);
        startActivity(intent);


    }


}
