package com.makariosawards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerTestActivity extends AppCompatActivity {

    RecyclerView verticalRecyclerView;
    VerticalAdapter verticalAdapter;
    ArrayList<VerticalModel> verticalModelArrayList;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference membersRef = database.getReference("Nominees");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);

        verticalModelArrayList = new ArrayList<>();

        verticalRecyclerView = findViewById(R.id.recyclerview);
        verticalRecyclerView.setHasFixedSize(true);

        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        verticalAdapter = new VerticalAdapter(getApplicationContext(), verticalModelArrayList);

        verticalRecyclerView.setAdapter(verticalAdapter);

        setData();
    }

    private void setData() {

            final VerticalModel verticalModel = new VerticalModel();
            //verticalModel.setTitle("Title: ");
            final ArrayList<HorizontalModel> horizontalModelArrayList = new ArrayList<>();

                membersRef.orderByChild("firstName").addValueEventListener(new ValueEventListener() {
                    HorizontalModel horizontalModel = new HorizontalModel();

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        verticalModelArrayList.clear();

                        if (verticalRecyclerView.getAdapter() == null) {
                            VerticalAdapter verticalAdapter = new VerticalAdapter(getApplicationContext(), verticalModelArrayList);
                            verticalRecyclerView.setAdapter(verticalAdapter);
                        } else {
                            ((VerticalAdapter) verticalRecyclerView.getAdapter()).refill(verticalModelArrayList);
                        }

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            horizontalModel = ds.getValue(HorizontalModel.class);
                            horizontalModelArrayList.add(horizontalModel);
                        }

                        verticalModel.setHorizontalModelArrayList(horizontalModelArrayList);
                        verticalModelArrayList.add(verticalModel);


                        verticalAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




    }
}
