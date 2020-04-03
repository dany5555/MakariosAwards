package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Relive2019FullAwardsActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relive2019_full_awards);

        youTubePlayerView = findViewById(R.id.player);
        getLifecycle().addObserver(youTubePlayerView);

    }
}
