package com.makariosawards;

import org.json.JSONException;
import org.json.JSONObject;

public class Relive2019FullAwardsModel {

    String fullName;
    String pictureUrl;

    public Relive2019FullAwardsModel(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                fullName = jsonObject.getString("fullName");
                pictureUrl = jsonObject.getString("pictureUrl");
            } catch (JSONException e) {
                // Error
            }
        }
    }


}
