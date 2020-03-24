package com.makariosawards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewCastVoteActivity extends AppCompatActivity implements CastVoteListAdapter.OnNomineeListener {

    TextView categoryTitle, voteStatus;
    Button castVoteButton, changeVoteButton;
    RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categoriesRef = database.getReference("Categories");
    DatabaseReference votersRef = database.getReference("Voters");
    DatabaseReference nomineesRef = database.getReference("Nominees");
    DatabaseReference isVotingEnabledRef = database.getReference("isVotingEnabled");
    String categoryName, nomineeUid;
    String currentVote;

    // String used to check is voting is still open or not.
    String checkVoting;

    ArrayList<NomineesModel> nomineesModelArrayList = new ArrayList<>();
    NomineesModel nomineesModel;
    CastVoteListAdapter castVoteListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cast_vote);

        categoryName = getIntent().getStringExtra("categoryTitle");
        nomineeUid = getIntent().getStringExtra("nomineeUid");

        categoryTitle = findViewById(R.id.category_title);
        recyclerView = findViewById(R.id.recyclerView);
        voteStatus = findViewById(R.id.vote_status);
        castVoteButton = findViewById(R.id.cast_vote_button);
        changeVoteButton = findViewById(R.id.change_vote_button);

        categoryTitle.setText(categoryName);

        // Retrieves the flag that tells us if the voting window is either open or closed.
        isVotingEnabledRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                checkVoting = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        votersRef.child(nomineeUid).child(categoryName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentVote = dataSnapshot.getValue().toString();

                //Debugging
                //Log.e("currentVote", "first current vote is " + currentVote);

                if (TextUtils.equals(currentVote, "none")) {
                    castVoteButton.setEnabled(true);
                    changeVoteButton.setEnabled(false);
                    //Recyclerview enabled false?
                    //recyclerView.setEnabled(true);
                    //recyclerView.setClickable(true);
                    recyclerView.setVisibility(View.VISIBLE);
                    voteStatus.setText("Please select a nominee above");
                } else {
                    castVoteButton.setEnabled(false);
                    changeVoteButton.setEnabled(true);
                    //Recyclerview set enabled true?
                    //recyclerView.setEnabled(false);
                    //recyclerView.setClickable(false);
                    recyclerView.setVisibility(View.GONE);

                    nomineesRef.child(currentVote).child("fullName").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            voteStatus.setText("You voted for " + dataSnapshot.getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Debugging
        //Log.e("currentVote", "second current vote is " + currentVote);

        categoriesRef.child(categoryName).child("Nominees").orderByChild("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*if (recyclerView.getAdapter() == null) {
                    CastVoteListAdapter castVoteListAdapter = new CastVoteListAdapter(nomineesModelArrayList, getApplicationContext(), this);
                    recyclerView.setAdapter(castVoteListAdapter);
                } else {
                    ((CastVoteListAdapter) recyclerView.getAdapter()).refill(nomineesModelArrayList);
                }*/

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    nomineesModel = ds.getValue(NomineesModel.class);

                    nomineesModelArrayList.add(nomineesModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        castVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.equals(checkVoting, "true")) {
                    if (TextUtils.equals(currentVote, "none")) {
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
                                //Recyclerview set enabled?
                                //recyclerView.setEnabled(false);
                                //recyclerView.setClickable(false);
                                recyclerView.setVisibility(View.GONE);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        votersRef.child(nomineeUid).child(categoryName).setValue(currentVote);

                    }
                } else if (TextUtils.equals(checkVoting, "false")) {
                    Toast.makeText(getApplicationContext(), "The voting window is currently closed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        changeVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.equals(checkVoting, "true")) {
                    categoriesRef.child(categoryName).child("Nominees").child(currentVote).child("votes").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int votes = Integer.valueOf(dataSnapshot.getValue().toString());
                            votes -= 1;
                            categoriesRef.child(categoryName).child("Nominees").child(currentVote).child("votes").setValue(votes);

                            castVoteButton.setEnabled(true);
                            changeVoteButton.setEnabled(false);
                            //Recyckerview enables true?
                            //recyclerView.setEnabled(true);
                            //recyclerView.setClickable(true);
                            recyclerView.setVisibility(View.VISIBLE);

                            voteStatus.setText("Select Nominee above");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    votersRef.child(nomineeUid).child(categoryName).setValue("none");
                } else if (TextUtils.equals(checkVoting, "false")) {
                    Toast.makeText(getApplicationContext(), "The voting window is currently closed. No changes can be made.", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //Recyclerview setup
        castVoteListAdapter = new CastVoteListAdapter(nomineesModelArrayList, getApplicationContext(), currentVote, checkVoting, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(castVoteListAdapter);



    }

    public void onNomineeClicked(int position) {
        //Get selected nominee name from the recyclerview once it's clicked on.
        currentVote = nomineesModelArrayList.get(position).getUid();

        voteStatus.setText("You have selected " + nomineesModelArrayList.get(position).getFirstName());
    }
}
