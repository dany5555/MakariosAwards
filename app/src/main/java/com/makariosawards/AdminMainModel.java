package com.makariosawards;

public class AdminMainModel {

    String categoryUid;

    public AdminMainModel() {}

    public AdminMainModel(String categoryUid) {
        this.categoryUid = categoryUid;
    }

    public String getCategoryUid() {
        return categoryUid;
    }

    public void setCategoryUid(String categoryUid) {
        this.categoryUid = categoryUid;
    }
}
