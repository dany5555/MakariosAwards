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
 * A simple {@link Fragment} subclass.
 */
public class LaziestPersonInPoetryFragment extends Fragment {

    ListView listView;
    TopThreeListModel topThreeListModel;
    TopThreeAdapter topThreeAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference categorie = database.getReference("Categories").child("Laziest Person in Poetry").child("Nominees");

    ArrayList<TopThreeListModel> topThreeListModelArrayList = new ArrayList<>();


    public LaziestPersonInPoetryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_laziest_person_in_poetry, container, false);

        listView = v.findViewById(R.id.top_3_list);

        topThreeListModel = new TopThreeListModel();
        topThreeAdapter = new TopThreeAdapter(getActivity(), topThreeListModelArrayList);
        listView.setAdapter(topThreeAdapter);

        categorie.orderByChild("votes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (listView.getAdapter() == null) {
                    TopThreeAdapter adapter = new TopThreeAdapter(getActivity(), topThreeListModelArrayList);
                    listView.setAdapter(adapter);
                } else {
                    ((TopThreeAdapter)listView.getAdapter()).refill(topThreeListModelArrayList);
                }

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    topThreeListModel = ds.getValue(TopThreeListModel.class);
                    topThreeListModelArrayList.add(topThreeListModel);
                }

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
