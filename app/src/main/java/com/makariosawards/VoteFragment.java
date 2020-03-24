package com.makariosawards;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoteFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Categories");
    VoteCategoryListAdapter voteCategoryListAdapter;
    ArrayList<AdminMainModel> adminMainModelArrayList = new ArrayList<>();
    AdminMainModel adminMainModel;

    String uid;
    HomeActivity homeActivity;

    public VoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote, container, false);

        homeActivity = (HomeActivity) getActivity();
        uid = homeActivity.getUid();

        recyclerView = view.findViewById(R.id.recyclerview);

        adminMainModel = new AdminMainModel();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (recyclerView.getAdapter() == null) {
                    VoteCategoryListAdapter voteCategoryListAdapter = new VoteCategoryListAdapter(adminMainModelArrayList, getActivity(), uid);
                    recyclerView.setAdapter(voteCategoryListAdapter);
                } else {
                    ((VoteCategoryListAdapter) recyclerView.getAdapter()).refill(adminMainModelArrayList);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    adminMainModel = ds.getValue(AdminMainModel.class);

                    adminMainModelArrayList.add(adminMainModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        voteCategoryListAdapter = new VoteCategoryListAdapter(adminMainModelArrayList, getActivity(), uid);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(voteCategoryListAdapter);




        return view;
    }

}
