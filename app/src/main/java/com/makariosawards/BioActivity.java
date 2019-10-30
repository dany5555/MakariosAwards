package com.makariosawards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This activity is responsible for displaying the personal bio information once a nominee is
 * selected from the NomineesActivity grid view.
 */
public class BioActivity extends AppCompatActivity {

    // Declare variables.
    ImageView nomineePicture;
    TextView nomineeName, nomineeRoll, nomineeAge, nomineeNationality;

    // Declare database variables.
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nomineeRef;

    // Declare string variable for later use.
    String nomineeUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_alternate);

        /*
        We use the passed data from the NomineesActivity intent and store it in their respective
        variables for later use. In this case, the nomineeUid variable holds the id of the nominee
        that was clicked on
         */
        nomineeUid = getIntent().getStringExtra("nomineeUid");

        // Set the database reference.
        nomineeRef = database.getReference("Nominees").child(nomineeUid);

        // Casting UI elements.
        nomineePicture = findViewById(R.id.nominee_picture);
        nomineeName = findViewById(R.id.nominee_name);
        nomineeRoll = findViewById(R.id.nominee_roll);
        nomineeAge = findViewById(R.id.nominee_age);
        nomineeNationality = findViewById(R.id.nominee_nationality);

        // Access database reference.
        nomineeRef.child("fullName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Set the text from the text view with data from database.
                nomineeName.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Access database reference.
        nomineeRef.child("pictureUrl").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Set the image from the url from the database.
                Glide.with(getApplicationContext()).load(dataSnapshot.getValue().toString()).into(nomineePicture);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Access database reference.
        nomineeRef.child("group").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Set the text from the text view with data from database.
                nomineeRoll.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Access database reference.
        nomineeRef.child("age").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Set the text from the text view with data from database.
                nomineeAge.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Access database reference.
        nomineeRef.child("nationality").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Set the text from the text view with data from database.
                nomineeNationality.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
