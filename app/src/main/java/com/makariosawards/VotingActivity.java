package com.makariosawards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VotingActivity extends AppCompatActivity {

    ListView categoryList;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categoriesRef = database.getReference("Categories");
    DatabaseReference votersRef = database.getReference("Voters");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alternate_activity_voting);
    }
}
