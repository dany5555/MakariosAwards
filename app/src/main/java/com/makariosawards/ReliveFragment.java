package com.makariosawards;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReliveFragment extends Fragment {

    Button reliveButton, nomineesButton;
    ImageView background;

    String url = "https://lh3.googleusercontent.com/bEJzExKEjvGdG5G7w1Q0w8nkc9vIBWxs3XeaFWQ_POqSWkCpqSW-8umOnrn8QD5XVAFFx4dsRGrq0hNiOI_RdSCmeDgEB4PsivKgw-EKJ_R6lZcNYo49aA8SDZf3NHwv7jD0JKbqBQ=w2108-h1245-no";

    public ReliveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relive, container, false);

        reliveButton = view.findViewById(R.id.relivebutton);
        nomineesButton = view.findViewById(R.id.nominees2019Button);
        background = view.findViewById(R.id.imageView12);

        Glide.with(this).load(url).transition(withCrossFade()).into(background);

        reliveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take me to activity.
                Intent intent = new Intent(getActivity(), Relive2019AwardsActivity.class);
                startActivity(intent);
            }
        });

        nomineesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take me to activity.
                Intent intent = new Intent(getActivity(), Relive2019NomineesActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
