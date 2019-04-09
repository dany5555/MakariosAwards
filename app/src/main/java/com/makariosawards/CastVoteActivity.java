package com.makariosawards;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CastVoteActivity extends AppCompatActivity {

    TextView categoryTitle, votingFor;
    Button castVoteButton, changeVoteButton;
    GridView gridView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categoriesRef = database.getReference("Categories");
    DatabaseReference votersRef = database.getReference("Voters");
    DatabaseReference nomineesRef = database.getReference("Nominees");
    String categoryName, nomineeUid;
    String currentVote;
    //String votedFor;



    ArrayList<NomineesModel> nomineesModelArrayList = new ArrayList<>();
    NomineesModel nomineesModel;
    NomineesAdapter nomineesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_vote);

        categoryName = getIntent().getStringExtra("categoryTitle");
        nomineeUid = getIntent().getStringExtra("nomineeUid");

        categoryTitle = findViewById(R.id.categoryName);
        gridView = findViewById(R.id.gridView);
        votingFor = findViewById(R.id.votingFor);
        castVoteButton = findViewById(R.id.cast_vote_button);
        changeVoteButton = findViewById(R.id.change_vote_button);
        gridView.setAdapter(nomineesAdapter);

        votersRef.child(nomineeUid).child(categoryName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentVote = dataSnapshot.getValue().toString();
                Log.e("currentVote", "first current vote is " + currentVote);

                if (TextUtils.equals(currentVote, "none")) {
                    castVoteButton.setEnabled(true);
                    changeVoteButton.setEnabled(false);
                    gridView.setEnabled(true);
                    votingFor.setText("Select nominee above");
                } else {
                    castVoteButton.setEnabled(false);
                    changeVoteButton.setEnabled(true);
                    gridView.setEnabled(false);

                    nomineesRef.child(currentVote).child("fullName").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            votingFor.setText("You voted for " + dataSnapshot.getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    //votingFor.setText("Voted for " + currentVote);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.e("currentVote", "second current vote is " + currentVote);



        categoriesRef.child(categoryName).child("Nominees").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (gridView.getAdapter() == null) {
                    NomineesAdapter teamsAdapter = new NomineesAdapter(getApplicationContext(), nomineesModelArrayList);
                    gridView.setAdapter(teamsAdapter);
                } else {
                    ((NomineesAdapter)gridView.getAdapter()).refill(nomineesModelArrayList);
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    nomineesModel = ds.getValue(NomineesModel.class);
                    nomineesModelArrayList.add(nomineesModel);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        categoriesRef.child(categoryName).child("categoryUid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoryTitle.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        castVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.equals(currentVote, "none")) {
                    // Tell the user to select a nominee from the list
                    Toast.makeText(getApplicationContext(), "Please select a nominee from the list above", Toast.LENGTH_SHORT).show();
                } else {


                    categoriesRef.child(categoryName).child("Nominees").child(currentVote).child("votes").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int votes = Integer.valueOf(dataSnapshot.getValue().toString());
                            votes += 1;
                            categoriesRef.child(categoryName).child("Nominees").child(currentVote).child("votes").setValue(votes);

                            castVoteButton.setEnabled(false);
                            changeVoteButton.setEnabled(true);
                            gridView.setEnabled(false);

                            votingFor.setText("Voted for " + currentVote);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    votersRef.child(nomineeUid).child(categoryName).setValue(currentVote);
                }
            }
        });

        changeVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoriesRef.child(categoryName).child("Nominees").child(currentVote).child("votes").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int votes = Integer.valueOf(dataSnapshot.getValue().toString());
                        votes -= 1;
                        categoriesRef.child(categoryName).child("Nominees").child(currentVote).child("votes").setValue(votes);

                        castVoteButton.setEnabled(true);
                        changeVoteButton.setEnabled(false);
                        gridView.setEnabled(true);

                        votingFor.setText("Select nominee above");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                votersRef.child(nomineeUid).child(categoryName).setValue("none");
                currentVote = "none";

            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomineesModel = new NomineesModel();
                nomineesModel = nomineesModelArrayList.get(i);

                currentVote = String.valueOf(nomineesModel.getUid());

                votingFor.setText("You have selected " + nomineesModel.getFirstName());









            }
        });


    }
}
