package com.makariosawards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

public class VideoLinksModel {

    String videoLink;

    public VideoLinksModel(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                videoLink = jsonObject.getString("videoLink");
            } catch (JSONException e) {
                // Error
            }
        }
    }
}
