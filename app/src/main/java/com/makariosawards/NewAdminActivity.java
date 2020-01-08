package com.makariosawards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class NewAdminActivity extends AppCompatActivity {

    // New code being added to make it more dynamic.





    // Database references.
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference c1 = database.getReference("Categories").child("Best Directiva Member").child("Nominees");
    DatabaseReference c2 = database.getReference("Categories").child("Best Smile").child("Nominees");
    //DatabaseReference c3 = database.getReference("Categories").child("Quietest").child("Nominees");
    //DatabaseReference c4 = database.getReference("Categories").child("Quietest").child("Nominees");

    //New Code
    DatabaseReference categoriesRef = database.getReference("Categories");
    RecyclerView mainRecyclerView;
    AdminMainAdapter adminMainAdapter;
    ArrayList<AdminMainModel> adminMainModelArrayList = new ArrayList<>();
    AdminMainModel adminMainModel;

    ArrayList<String> categoryTitlesArrayList = new ArrayList<>();
    // -----

    TopThreeListModel topThreeListModel;

    // Adapters.
    //AdminRecyclerViewAdapter c1_adapter;
    //AdminRecyclerViewAdapter c2_adapter;
    //AdminRecyclerViewAdapter c3_adapter;
    //AdminRecyclerViewAdapter c4_adapter;


    // Array lists.
    //ArrayList<TopThreeListModel> c1_list = new ArrayList<>();
    //ArrayList<TopThreeListModel> c2_list = new ArrayList<>();
    //ArrayList<TopThreeListModel> c3_list = new ArrayList<>();
    //ArrayList<TopThreeListModel> c4_list = new ArrayList<>();


    // Other UI.
    Toolbar toolbar;
    //RecyclerView c1_recyclerView, c2_recyclerView, c3_recyclerView, c4_recyclerView;
    //ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_admin);

        toolbar = findViewById(R.id.toolbar);

        // Recycler views.
        //c1_recyclerView = findViewById(R.id.c1_recycler_view);
        //c2_recyclerView = findViewById(R.id.c2_recycler_view);
        //c3_recyclerView = findViewById(R.id.c3_recycler_view);
        //c4_recyclerView = findViewById(R.id.c4_recycler_view);

        // New Code

        adminMainModel = new AdminMainModel();

        categoriesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminMainModelArrayList.clear();

                if (mainRecyclerView.getAdapter() == null) {
                    AdminMainAdapter adminMainAdapter = new AdminMainAdapter(adminMainModelArrayList, getApplicationContext());
                    mainRecyclerView.setAdapter(adminMainAdapter);
                } else {
                    ((AdminMainAdapter) mainRecyclerView.getAdapter()).refill(adminMainModelArrayList);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    adminMainModel = ds.getValue(AdminMainModel.class);

                    adminMainModelArrayList.add(adminMainModel);
                    Log.e("lol", "Go the categories: " + adminMainModelArrayList);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mainRecyclerView = findViewById(R.id.main_recyclerview);
        adminMainAdapter = new AdminMainAdapter(adminMainModelArrayList, getApplicationContext());

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainRecyclerView.setAdapter(adminMainAdapter);




        //-----------



        //scrollView = findViewById(R.id.scrollView);

        setSupportActionBar(toolbar);

        // Display icon on the toolbar.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_makarios_awards_logo_with_insignia_horizontal_taller);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Fix scrolling issues.
        //scrollView.setVerticalScrollBarEnabled(false);
        //c1_recyclerView.setNestedScrollingEnabled(false);
        //c2_recyclerView.setNestedScrollingEnabled(false);
        //c3_recyclerView.setNestedScrollingEnabled(false);
        //c4_recyclerView.setNestedScrollingEnabled(false);



        //topThreeListModel = new TopThreeListModel();

        // Make new adapters.
        //c1_adapter = new AdminRecyclerViewAdapter(getApplicationContext(), c1_list);
        //c2_adapter = new AdminRecyclerViewAdapter(getApplicationContext(), c2_list);
        //c3_adapter = new AdminRecyclerViewAdapter(getApplicationContext(), c3_list);
        //c4_adapter = new AdminRecyclerViewAdapter(getApplicationContext(), c4_list);

        // Set RecyclerView layout managers.
        //c1_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //c2_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //c3_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //c4_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // Set adapters.
        //c1_recyclerView.setAdapter(c1_adapter);
        //c2_recyclerView.setAdapter(c2_adapter);
        //c3_recyclerView.setAdapter(c3_adapter);
        //c4_recyclerView.setAdapter(c3_adapter);


        /*c1.orderByChild("votes").limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                c1_list.clear();

                if (c1_recyclerView.getAdapter() == null) {
                    AdminRecyclerViewAdapter adminRecyclerViewAdapter = new AdminRecyclerViewAdapter(getApplicationContext(), c1_list);
                    c1_recyclerView.setAdapter(adminRecyclerViewAdapter);
                } else {
                    ((AdminRecyclerViewAdapter) c1_recyclerView.getAdapter()).refill(c1_list);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    topThreeListModel = ds.getValue(TopThreeListModel.class);
                    c1_list.add(topThreeListModel);
                }

                ArrayList<TopThreeListModel> reversedList = c1_list;
                Collections.reverse(reversedList);
                c1_list = reversedList;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        /*c2.orderByChild("votes").limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //c2_list.clear();

                if (c2_recyclerView.getAdapter() == null) {
                    AdminRecyclerViewAdapter adminRecyclerViewAdapter = new AdminRecyclerViewAdapter(getApplicationContext(), c2_list);
                    c2_recyclerView.setAdapter(adminRecyclerViewAdapter);
                } else {
                    ((AdminRecyclerViewAdapter) c2_recyclerView.getAdapter()).refill(c2_list);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    topThreeListModel = ds.getValue(TopThreeListModel.class);
                    c2_list.add(topThreeListModel);
                }

                ArrayList<TopThreeListModel> reversedList = c2_list;
                Collections.reverse(reversedList);
                c2_list = reversedList;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        /**c3.orderByChild("votes").limitToLast(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                c3_list.clear();

                if (c3_recyclerView.getAdapter() == null) {
                    AdminRecyclerViewAdapter adminRecyclerViewAdapter = new AdminRecyclerViewAdapter(getApplicationContext(), c3_list);
                    c3_recyclerView.setAdapter(adminRecyclerViewAdapter);
                } else {
                    ((AdminRecyclerViewAdapter) c3_recyclerView.getAdapter()).refill(c3_list);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    topThreeListModel = ds.getValue(TopThreeListModel.class);
                    c3_list.add(topThreeListModel);
                }

                ArrayList<TopThreeListModel> reversedList = c3_list;
                Collections.reverse(reversedList);
                c3_list = reversedList;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/






    }
}
