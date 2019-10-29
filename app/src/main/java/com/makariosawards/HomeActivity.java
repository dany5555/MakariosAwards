package com.makariosawards;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    String nomineeUid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference loggedInRef = database.getReference("Nominees");

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    VoteFragment voteFragment;
    HomeFragment homeFragment;
    NomineesFragment nomineesFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbar);
        voteFragment = new VoteFragment();
        homeFragment = new HomeFragment();
        nomineesFragment = new NomineesFragment();

        setSupportActionBar(toolbar);

        // Display icon in the toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_makarios_awards_logo_with_insignia_horizontal_taller);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.vote_section :
                        setFragment(voteFragment);
                        return true;
                    case R.id.home_section :
                        setFragment(homeFragment);
                        return true;
                    case R.id.nominees_section :
                        setFragment(nomineesFragment);
                        return true;
                    default :
                        return false;
                }
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.home_section);


        nomineeUid = getIntent().getStringExtra("id");

        // Displays the name of the person that is currently logged in.
        loggedInRef.child(nomineeUid).child("firstName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //welcomeText.setText("Welcome " + dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void editProfile(MenuItem menuItem) {
        Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
        intent.putExtra("nomineeUid", nomineeUid);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu_layout, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
