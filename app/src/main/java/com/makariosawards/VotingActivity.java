package com.makariosawards;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VotingActivity extends AppCompatActivity {

    String nomineeUid;

    ListView categoryList;
    CategoriesModel categoriesModel;
    CategoriesAdapter categoriesAdapter;
    ArrayList<CategoriesModel> categoriesModelArrayList = new ArrayList<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categoriesRef = database.getReference("Categories");
    DatabaseReference votersRef = database.getReference("Voters");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alternate_activity_voting);

        nomineeUid = getIntent().getStringExtra("nomineeUid");

        categoryList = findViewById(R.id.category_list);

        categoriesModel = new CategoriesModel();
        categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categoriesModelArrayList);

        categoryList.setAdapter(categoriesAdapter);

        categoriesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (categoryList.getAdapter() == null) {
                    CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getApplicationContext(), categoriesModelArrayList);
                    categoryList.setAdapter(categoriesAdapter);
                } else {
                    ((CategoriesAdapter)categoryList.getAdapter()).refill(categoriesModelArrayList);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    categoriesModel = ds.getValue(CategoriesModel.class);
                    //String status = matchesModel.getMatchStatus();
                    categoriesModelArrayList.add(categoriesModel);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                categoriesModel = new CategoriesModel();
                categoriesModel = categoriesModelArrayList.get(i);

                Intent intent = new Intent(getApplicationContext(), CastVoteActivity.class);

                String categoryName = categoriesModel.getCategoryUid();

                intent.putExtra("categoryTitle", categoryName);
                intent.putExtra("nomineeUid", nomineeUid);

                startActivity(intent);
            }
        });
    }
}
