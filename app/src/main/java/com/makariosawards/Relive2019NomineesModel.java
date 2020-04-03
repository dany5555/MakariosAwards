package com.makariosawards;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

public class Relive2019NomineesModel {

    String firstname;
    String lastName;
    String fullName;
    String pictureUrl;
    String muisc;
    String poetry;
    String drama;
    String directiva;

    public Relive2019NomineesModel(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                firstname = jsonObject.getString("firstName");
                lastName = jsonObject.getString("lastName");
                fullName = jsonObject.getString("fullName");
                directiva = jsonObject.getString("directiva");
                poetry = jsonObject.getString("poetry");
                muisc = jsonObject.getString("music");
                drama = jsonObject.getString("drama");
                pictureUrl = jsonObject.getString("pictureUrl");
            } catch (JSONException e) {
                // Error
            }
        }
    }

    public static Comparator<Relive2019NomineesModel> sortByFirstName = new Comparator<Relive2019NomineesModel>() {
        @Override
        public int compare(Relive2019NomineesModel nominee1, Relive2019NomineesModel nominee2) {
            String nomineeName1 = nominee1.firstname.toUpperCase();
            String nomineeName2 = nominee2.firstname.toUpperCase();

            return nomineeName1.compareTo(nomineeName2);
        }
    };
}
