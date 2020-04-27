package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Relive2019AwardsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Relive2019WinnersModel> relive2019WinnersModelArrayList;
    ArrayList<Relive2019WinnersModel> finalList;
    ArrayList<Relive2019CategoriesModel> categoriesList;
    ArrayList<VideoLinksModel> videoLinksList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relive2019_awards);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        finalList = new ArrayList<>();
        categoriesList = new ArrayList<>();
        videoLinksList = new ArrayList<>();

        getCategoriesJSON();
        getVideoLinksJSON();

        getJSON(recyclerView, "Best Directiva Member", "fullStandings2019.json");
        getJSON(recyclerView, "Best Gringo", "fullStandings2019.json");
        getJSON(recyclerView, "Best Smile", "fullStandings2019.json");
        getJSON(recyclerView, "First in Line for Potluck", "fullStandings2019.json");
        getJSON(recyclerView, "Kindest", "fullStandings2019.json");
        getJSON(recyclerView, "Loudest Laugh", "fullStandings2019.json");
        getJSON(recyclerView, "Most Dramatic", "fullStandings2019.json");
        getJSON(recyclerView, "Most Passionate", "fullStandings2019.json");
        getJSON(recyclerView, "Most Punctual", "fullStandings2019.json");
        getJSON(recyclerView, "Quietest", "fullStandings2019.json");
        getJSON(recyclerView, "Sleepiest", "fullStandings2019.json");

        recyclerView.setAdapter(new Relive2019WinnersAdapter(finalList, categoriesList, videoLinksList, this));
    }

    public void getCategoriesJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSON("categories.json"));
            JSONArray categories = obj.getJSONArray("Categories");

            int j = categories.length();
            Log.e("lol","Categories: " + j);

            for (int i = 0; i < categories.length(); i++) {
                JSONObject objc = categories.getJSONObject(i);
                categoriesList.add(new Relive2019CategoriesModel(objc));
            }
        } catch (JSONException e) {

        }
    }

    public void getVideoLinksJSON() {
        try {
            JSONObject obj = new JSONObject(loadJSON("videoLinks.json"));
            JSONArray videoLinks = obj.getJSONArray("VideoLinks");

            int j = videoLinks.length();
            Log.e("lol","Categories: " + j);

            for (int i = 0; i < videoLinks.length(); i++) {
                JSONObject objc = videoLinks.getJSONObject(i);
                videoLinksList.add(new VideoLinksModel(objc));
            }
        } catch (JSONException e) {

        }
    }

    public void getJSON(RecyclerView recyclerView, String categoryName, String jsonFileName) {
        try {
            relive2019WinnersModelArrayList = new ArrayList<>();
            JSONObject obj = new JSONObject(loadJSON(jsonFileName));
            JSONArray winners = obj.getJSONArray(categoryName);
            //int j = winners.length();
            //Log.e("lol","Object length: " + j);

            for (int i = 0; i < winners.length(); i++) {
                JSONObject objc = winners.getJSONObject(i);
                relive2019WinnersModelArrayList.add(new Relive2019WinnersModel(objc));
            }

            Collections.sort(relive2019WinnersModelArrayList, Relive2019WinnersModel.sortByVotes);

            finalList.add(relive2019WinnersModelArrayList.get(0));

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
