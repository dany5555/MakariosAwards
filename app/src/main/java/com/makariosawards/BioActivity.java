package com.makariosawards;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BioActivity extends AppCompatActivity {

    ImageView nomineePicture;
    TextView nomineeName, nomineeRoll, nomineeDescription, nomineeAge, nomineeNationality;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nomineeRef;
    String nomineeUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        nomineeUid = getIntent().getStringExtra("nomineeUid");
        nomineeRef = database.getReference("Nominees").child(nomineeUid);

        nomineePicture = findViewById(R.id.nominee_picture);
        nomineeName = findViewById(R.id.nominee_name);
        nomineeDescription = findViewById(R.id.nominee_description);
        nomineeRoll = findViewById(R.id.nominee_roll);
        nomineeAge = findViewById(R.id.nominee_age);
        nomineeNationality = findViewById(R.id.nominee_nationality);

        nomineeRef.child("fullName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomineeName.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nomineeRef.child("pictureUrl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(getApplicationContext()).load(dataSnapshot.getValue().toString()).into(nomineePicture);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nomineeRef.child("group").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomineeRoll.setText("Roll in Club: " + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nomineeRef.child("nomineeDescription").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomineeDescription.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nomineeRef.child("age").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomineeAge.setText("Age: " + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        nomineeRef.child("nationality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomineeNationality.setText("Nationality: " + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
