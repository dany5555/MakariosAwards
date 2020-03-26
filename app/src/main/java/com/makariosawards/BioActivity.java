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

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * This activity is responsible for displaying the personal bio information once a nominee is
 * selected from the NomineesActivity grid view.
 */
public class BioActivity extends AppCompatActivity {

    // Declare variables.
    ImageView nomineePicture;
    TextView nomineeName, nomineeRoll, nomineeAge, nomineeNationality;

    // Declare string variable for later use.
    String fullName, pictureUrl, age, nationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_alternate);

        // Get data sent from the intent.
        fullName = getIntent().getStringExtra("fullName");
        pictureUrl  = getIntent().getStringExtra("pictureUrl");
        age = getIntent().getStringExtra("age");
        nationality = getIntent().getStringExtra("nationality");

        // Casting UI elements.
        nomineePicture = findViewById(R.id.nominee_picture);
        nomineeName = findViewById(R.id.nominee_name);
        nomineeRoll = findViewById(R.id.nominee_roll);
        nomineeAge = findViewById(R.id.nominee_age);
        nomineeNationality = findViewById(R.id.nominee_nationality);

        // Set data to respective UI elements.
        nomineeName.setText(fullName);
        nomineeAge.setText(age);
        nomineeNationality.setText(nationality);
        Glide.with(this).load(pictureUrl).transition(withCrossFade()).into(nomineePicture);




    }


}
