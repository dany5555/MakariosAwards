package com.makariosawards;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NomineesActivity extends AppCompatActivity {

    GridView gridView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nomineesRef = database.getReference("Nominees");
    ArrayList<NomineesModel> nomineesModelArrayList = new ArrayList<>();
    NomineesModel nomineesModel;
    NomineesAdapter nomineesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominees);

        gridView = findViewById(R.id.gridview);
        gridView.setAdapter(nomineesAdapter);


        nomineesRef.orderByChild("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (gridView.getAdapter() == null) {
                    NomineesAdapter teamsAdapter = new NomineesAdapter(getApplicationContext(), nomineesModelArrayList);
                    gridView.setAdapter(teamsAdapter);
                } else {
                    ((NomineesAdapter)gridView.getAdapter()).refill(nomineesModelArrayList);
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    nomineesModel = ds.getValue(NomineesModel.class);
                    nomineesModelArrayList.add(nomineesModel);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nomineesModel = new NomineesModel();
                nomineesModel = nomineesModelArrayList.get(position);

                Intent intent = new Intent(getApplicationContext(), BioActivity.class);

                String nomineeUid = String.valueOf(nomineesModel.getUid());
                intent.putExtra("nomineeUid", nomineeUid);
                startActivity(intent);
            }
        });
    }
}
