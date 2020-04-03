package com.makariosawards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

public class Relive2019CategoriesModel {

    String categoryName;


    public Relive2019CategoriesModel(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                categoryName = jsonObject.getString("categoryName");

            } catch (JSONException e) {
                // Error
            }
        }
    }

}
