package com.makariosawards;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView homeRecyclerview;
    //ScrollView scrollView;
    HomeFragmentAdapter homeFragmentAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference homeDataRef = database.getReference("HomeData");
    HomeDataModel homeDataModel;

    ArrayList<HomeDataModel> homeDataModelArrayList = new ArrayList<>();

    View view;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        homeRecyclerview = view.findViewById(R.id.home_recyclerView);
        //scrollView = view.findViewById(R.id.scrollview);

        //scrollView.setVerticalScrollBarEnabled(false);
        //homeRecyclerview.setNestedScrollingEnabled(false);

        homeDataModel = new HomeDataModel();

        homeFragmentAdapter = new HomeFragmentAdapter(homeDataModelArrayList, getActivity());
        homeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRecyclerview.setItemAnimator(new DefaultItemAnimator());
        homeRecyclerview.setAdapter(homeFragmentAdapter);

        homeDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                homeDataModelArrayList.clear();

                if (homeRecyclerview.getAdapter() == null) {
                    HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(homeDataModelArrayList, getActivity());
                    homeRecyclerview.setAdapter(homeFragmentAdapter);
                } else {
                    ((HomeFragmentAdapter) homeRecyclerview.getAdapter()).refill(homeDataModelArrayList);
                }

                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    homeDataModel = ds.getValue(HomeDataModel.class);
                    homeDataModelArrayList.add(homeDataModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return view;
    }

}
