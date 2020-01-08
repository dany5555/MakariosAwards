package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class AllVotesActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_votes);

        String categoryTitle = getIntent().getStringExtra("categoryTitle");

        toolbar = findViewById(R.id.all_votes_toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(categoryTitle);
        getSupportActionBar().setLogo(R.drawable.ic_makarios_single_logo_for_app);


    }
}
