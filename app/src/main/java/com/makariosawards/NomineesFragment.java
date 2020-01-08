package com.makariosawards;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
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
public class NomineesFragment extends Fragment {

    //GridView gridView;
    ScrollView scrollView;
    RecyclerView recyclerView;
    NomineesRecyclerviewAdapter nomineesRecyclerviewAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference membersRef = database.getReference("Nominees");
    NomineesModel nomineesModel;
    NomineesAdapter nomineesAdapter;

    ArrayList<NomineesModel> nomineesModelArrayList = new ArrayList<>();


    View view;


    public NomineesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_nominees_recyclerview, container, false);
        }

        recyclerView = view.findViewById(R.id.members_recycler_view);
        scrollView = view.findViewById(R.id.scrollview);
        scrollView.setVerticalScrollBarEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);

        nomineesModel = new NomineesModel();

        nomineesRecyclerviewAdapter = new NomineesRecyclerviewAdapter(getActivity(), nomineesModelArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(nomineesRecyclerviewAdapter);

        //gridView = view.findViewById(R.id.gridview);
        //gridView.setNestedScrollingEnabled(false);
        //gridView.setAdapter(nomineesAdapter);

        membersRef.orderByChild("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nomineesModelArrayList.clear();

                if (recyclerView.getAdapter() == null) {
                    NomineesRecyclerviewAdapter nomineesRecyclerviewAdapter = new NomineesRecyclerviewAdapter(getActivity(), nomineesModelArrayList);
                    recyclerView.setAdapter(nomineesRecyclerviewAdapter);
                } else {
                    ((NomineesRecyclerviewAdapter) recyclerView.getAdapter()).refill(nomineesModelArrayList);
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


        /**gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nomineesModel = new NomineesModel();
                nomineesModel = nomineesModelArrayList.get(i);

                Intent intent = new Intent(getActivity(), BioActivity.class);

                String nomineeUid = String.valueOf(nomineesModel.getUid());
                intent.putExtra("nomineeUid", nomineeUid);
                startActivity(intent);
            }
        });*/


        return view;
    }

}
