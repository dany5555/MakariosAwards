package com.makariosawards;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    Button nomineesButton, voteButton;
    String nomineeUid;
    TextView welcomeText;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference loggedInRef = database.getReference("Nominees");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nomineesButton = findViewById(R.id. nominees_button);
        voteButton = findViewById(R.id.vote_button);
        welcomeText = findViewById(R.id.welcomeText);

        nomineeUid = getIntent().getStringExtra("id");

        // Displays the name of the person that is currently logged in.
        loggedInRef.child(nomineeUid).child("firstName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                welcomeText.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nomineesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NomineesActivity.class);
                startActivity(intent);
            }
        });

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VotingActivity.class);
                intent.putExtra("nomineeUid", nomineeUid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
