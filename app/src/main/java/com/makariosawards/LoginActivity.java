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

public class LoginActivity extends AppCompatActivity {

    //EditText emailEditText, passwordEditText;
    //Button loginButton;
    EditText idEditText;
    Button submitButton;
    TextView adminTextView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //FirebaseAuth firebaseAuth;
    DatabaseReference ref = database.getReference("Nominees");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        //firebaseAuth = FirebaseAuth.getInstance();

        //emailEditText = findViewById(R.id.email_editText);
        //passwordEditText = findViewById(R.id.password_editText);
        //loginButton = findViewById(R.id.login_button);
        idEditText = findViewById(R.id.id_edittext);
        submitButton = findViewById(R.id.submitButton);
        adminTextView = findViewById(R.id.admin_textview);

        adminTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String id = idEditText.getText().toString();

                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(getApplicationContext(), "Please enter your voterID", Toast.LENGTH_SHORT).show();
                } else {
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(id)) {
                                Toast.makeText(getApplicationContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();
                                // Go to home activity and save id to display name of person that is voting and keep track of their votes.

                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Please enter a valid voterID", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }






            }
        });





        /*loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter an email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter your password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "logged in!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Nope", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });*/







    }

    @Override
    public void onBackPressed() {
    }
}
