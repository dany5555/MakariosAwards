package com.makariosawards;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class AdminLoginActivity extends AppCompatActivity {

    EditText idEditText;
    Button submitButton;
    TextView notAdminTextview;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Admin");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        idEditText = findViewById(R.id.id_edittext);
        submitButton = findViewById(R.id.submitButton);
        notAdminTextview = findViewById(R.id.admin_textview);

        notAdminTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = idEditText.getText().toString();

                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(getApplicationContext(), "Please enter your adminID", Toast.LENGTH_SHORT).show();
                } else {
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(id)) {
                                Toast.makeText(getApplicationContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();
                                // Go to home activity and save id to display name of person that is voting and keep track of their votes.

                                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
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

    @Override
    public void onBackPressed() {

    }
}
