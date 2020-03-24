package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FullVotingStandingsActivity extends AppCompatActivity {

    String categoryTitle;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_voting_standings);

        categoryTitle = getIntent().getStringExtra("category");

        title = findViewById(R.id.category_title);

        title.setText(categoryTitle);


    }
}
