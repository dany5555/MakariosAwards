package com.makariosawards;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * This activity handles the admin login process by getting the user input and verifying against
 * the information on the database.
 */
public class AdminLoginActivity extends AppCompatActivity {

    // Declare my variables for my UI elements.
    EditText idEditText;
    Button submitButton;
    TextView notAdminTextview;

    // Declaring database variables.
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Admin");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        // Casting UI elements.
        idEditText = findViewById(R.id.id_edittext);
        submitButton = findViewById(R.id.submitButton);
        notAdminTextview = findViewById(R.id.admin_textview);


        // In case the user is not an admin, this listener returns the user to the
        // non-admin login activity.
        notAdminTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // This listener takes the user's entered information and verifies it against the
        // information in the database.
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Store user entered ID in a string variable to use later for comparisons.
                final String id = idEditText.getText().toString();

                // Check if the ID field is empty and prompt the user to enter an ID.
                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(getApplicationContext(), "Please enter your adminID", Toast.LENGTH_SHORT).show();
                } else {

                    // Access database reference.
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // Check if the entered ID exists within hte database.
                            // If it exists, the user is redirected to the AdminActivity.
                            // If not, the user is prompted to enter a valid ID.
                            if (dataSnapshot.hasChild(id)) {
                                Toast.makeText(getApplicationContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();
                                // Go to home activity and save id to display firstName of person that is voting and keep track of their votes.

                                Intent intent = new Intent(getApplicationContext(), AdminBetterNewActivity.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter a valid adminID", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });




    }

    // Used to disable the back button in this activity.
    @Override
    public void onBackPressed() {

    }
}
