package com.makariosawards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminBetterNewActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categoriesRef = database.getReference("Categories");
    AdminBetterNewAdapter adminBetterNewAdapter;
    ArrayList<AdminMainModel> adminMainModelArrayList = new ArrayList<>();
    AdminMainModel adminMainModel;

    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_better_new);

        recyclerView = findViewById(R.id.recyclerview);
        toolbar = findViewById(R.id.toolbar);

        adminMainModel= new AdminMainModel();

        categoriesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (recyclerView.getAdapter() == null) {
                    AdminBetterNewAdapter adminBetterNewAdapter = new AdminBetterNewAdapter(adminMainModelArrayList, getApplicationContext());
                    recyclerView.setAdapter(adminBetterNewAdapter);
                } else {
                    ((AdminBetterNewAdapter) recyclerView.getAdapter()).refill(adminMainModelArrayList);
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    adminMainModel = ds.getValue(AdminMainModel.class);

                    adminMainModelArrayList.add(adminMainModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adminBetterNewAdapter = new AdminBetterNewAdapter(adminMainModelArrayList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adminBetterNewAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_makarios_awards_logo_with_insignia_horizontal_taller);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




    }
}
