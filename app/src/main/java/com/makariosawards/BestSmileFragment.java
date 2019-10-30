package com.makariosawards;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


/**
 * This fragment is responsible of loading all of the data pertaining to the specific category
 * into the viewpager underneath the tab layout in the admin side of the app.
 */
public class BestSmileFragment extends Fragment {

    // Declare variables
    ListView listView;
    TopThreeListModel topThreeListModel;
    TopThreeAdapter topThreeAdapter;

    // Declare database variables.
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categorie = database.getReference("Categories").child("Best Smile").child("Nominees");

    // Declare an array list to hold data coming from database for later manipulation.
    ArrayList<TopThreeListModel> topThreeListModelArrayList = new ArrayList<>();

    public BestSmileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_best_smile, container, false);

        // Casting UI elements.
        listView = v.findViewById(R.id.top_3_list);

        // Casting the model and the adapter for the list view.
        topThreeListModel = new TopThreeListModel();
        topThreeAdapter = new TopThreeAdapter(getActivity(), topThreeListModelArrayList);

        // Set the adapter to display the data in the list view.
        listView.setAdapter(topThreeAdapter);

        // Access database reference.
        categorie.orderByChild("votes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // We use these statements to avoid the list view from bouncing to the top when
                // an element is added or deleted from the database.
                if (listView.getAdapter() == null) {
                    TopThreeAdapter adapter = new TopThreeAdapter(getActivity(), topThreeListModelArrayList);
                    listView.setAdapter(adapter);
                } else {
                    ((TopThreeAdapter)listView.getAdapter()).refill(topThreeListModelArrayList);
                }

                // Pull the data from the data snapshot and add it to the array list.
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    topThreeListModel = ds.getValue(TopThreeListModel.class);
                    topThreeListModelArrayList.add(topThreeListModel);
                }

                /*
                 Invert the list order from the default ascending order to descending order.
                 This allows us to display the list from the highest number of votes to the
                 least number of votes.
                 */
                ArrayList<TopThreeListModel> reversedList = topThreeListModelArrayList;
                Collections.reverse(reversedList);
                topThreeListModelArrayList = reversedList;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}
