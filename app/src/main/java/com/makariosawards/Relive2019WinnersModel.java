package com.makariosawards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

public class Relive2019WinnersModel {

    String fullName;
    String pictureUrl;
    int votes;
    String uid;

    public Relive2019WinnersModel(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                fullName = jsonObject.getString("fullName");
                pictureUrl = jsonObject.getString("pictureUrl");
                votes = jsonObject.getInt("votes");
                uid = jsonObject.getString("uid");
            } catch (JSONException e) {
                // Error
            }
        }
    }

    public static Comparator<Relive2019WinnersModel> sortByVotes = new Comparator<Relive2019WinnersModel>() {
        @Override
        public int compare(Relive2019WinnersModel t1, Relive2019WinnersModel t2) {
            int vote1 = t1.votes;
            int vote2 = t2.votes;
            return vote2 - vote1;
        }
    };
}
