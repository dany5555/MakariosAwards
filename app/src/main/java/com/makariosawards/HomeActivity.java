package com.makariosawards;

import android.content.Intent;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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
    VotingUnavailableFragment votingUnavailableFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUid();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbar);
        voteFragment = new VoteFragment();
        homeFragment = new HomeFragment();
        nomineesFragment = new NomineesFragment();
        votingUnavailableFragment = new VotingUnavailableFragment();

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
                        setFragment(votingUnavailableFragment);
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

        // Displays the firstName of the person that is currently logged in.
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

    public String getUid() {
        nomineeUid = getIntent().getStringExtra("id");
        return nomineeUid;

    }
}
