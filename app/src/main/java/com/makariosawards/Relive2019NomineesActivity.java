package com.makariosawards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class Relive2019NomineesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Relive2019NomineesModel> relive2019NomineesModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relive2019_nominees);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        relive2019NomineesModelArrayList = new ArrayList<>();

        getJSON(recyclerView, relive2019NomineesModelArrayList, "Nominees", "nominees2019.json");


    }

    public void getJSON(RecyclerView recyclerView, ArrayList arrayList, String jsonNode, String jsonFileName) {
        try {
            JSONObject obj = new JSONObject(loadJSON(jsonFileName));
            JSONArray jsonArray = obj.getJSONArray(jsonNode);
            int j = obj.length();
            Log.e("lol","Object length: " + j);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objc = jsonArray.getJSONObject(i);
                relive2019NomineesModelArrayList.add(new Relive2019NomineesModel(objc));
            }

            Collections.sort(relive2019NomineesModelArrayList, Relive2019NomineesModel.sortByFirstName);

            recyclerView.setAdapter(new Relive2019NomineesAdapter(relive2019NomineesModelArrayList, this));
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
