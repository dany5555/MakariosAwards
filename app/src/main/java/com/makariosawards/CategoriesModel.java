package com.makariosawards;

public class CategoriesModel {

    int votes;
    String categoryUid;




    public CategoriesModel() {

    }

    public CategoriesModel(int votes, String categoryUid) {
        this.votes = votes;
        this.categoryUid = categoryUid;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(String categoryUid) {
        this.categoryUid = categoryUid;
    }
}
