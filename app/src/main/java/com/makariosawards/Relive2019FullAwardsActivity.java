package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Relive2019FullAwardsActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    RecyclerView recyclerView;
    TextView categoryTitle;
    ArrayList<Relive2019WinnersModel> fullList;
    String category;
    //ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relive2019_full_awards);

        category = getIntent().getStringExtra("category");
        Log.e("lol", "category name: " + category);



        recyclerView = findViewById(R.id.recyclerView);
        categoryTitle = findViewById(R.id.categoryTitle);
        //scrollView = findViewById(R.id.scrollView);
        youTubePlayerView = findViewById(R.id.player);
        getLifecycle().addObserver(youTubePlayerView);
        //scrollView.setVerticalScrollBarEnabled(false);
        //recyclerView.setNestedScrollingEnabled(false);

        categoryTitle.setText(category);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fullList = new ArrayList<>();

        getJSON(recyclerView, category, "fullStandings2019.json");

        recyclerView.setAdapter(new Relive2019FullAwardsAdapter(fullList, this));

    }

    public void getJSON(RecyclerView recyclerView, String categoryName, String jsonFileName) {
        try {
            JSONObject obj = new JSONObject(loadJSON(jsonFileName));
            JSONArray winners = obj.getJSONArray(categoryName);
            int j = winners.length();
            Log.e("lol","Nominees in this category: " + j);

            for (int i = 0; i < winners.length(); i++) {
                JSONObject objc = winners.getJSONObject(i);
                fullList.add(new Relive2019WinnersModel(objc));
            }

            Collections.sort(fullList, Relive2019WinnersModel.sortByVotes);
            Log.e("lol", "Full List size: " + fullList.size());

            //finalList.add(relive2019WinnersModelArrayList.get(0));

            //ViewCompat.setNestedScrollingEnabled(recyclerView, false);


        } catch (JSONException e) {

        }
    }

    public String loadJSON(String jsonFileName) {
        String json = null;
        try {
            InputStream stream = getAssets().open(jsonFileName);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
