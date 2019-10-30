package com.makariosawards;

import java.util.ArrayList;

public class VerticalModel {

    String title;
    ArrayList<HorizontalModel> horizontalModelArrayList;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<HorizontalModel> getHorizontalModelArrayList() {
        return horizontalModelArrayList;
    }

    public void setHorizontalModelArrayList(ArrayList<HorizontalModel> horizontalModelArrayList) {
        this.horizontalModelArrayList = horizontalModelArrayList;
    }
}
