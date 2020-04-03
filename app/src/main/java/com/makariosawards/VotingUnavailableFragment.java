package com.makariosawards;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VotingUnavailableFragment extends Fragment {

    VideoView videoView;



    public VotingUnavailableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.voting_not_availabe_layout, container, false);

        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youTubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);

        //videoView = view.findViewById(R.id.videoView);

        //String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.sheila;
        //Uri uri = Uri.parse(videoPath);
        //videoView.setVideoURI(uri);
        //videoView.seekTo(13000);
        //videoView.setBackground(getResources().getDrawable(R.drawable.ic_makarios_single_logo_light));


        //MediaController mediaController = new MediaController(getActivity());
        //videoView.setMediaController(mediaController);
        //mediaController.setAnchorView(videoView);




        return view;
    }

}
