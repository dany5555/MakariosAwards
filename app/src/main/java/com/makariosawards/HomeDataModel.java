package com.makariosawards;

public class HomeDataModel {

    String title, dateAndAuthor;
    String firstImageUrl, secondImageUrl;
    String firstParagraph, secondParagraph;
    String uid;
    String appLink;
    String viewType;

    public HomeDataModel() {}

    public HomeDataModel(String title, String dateAndAuthor, String firstImageUrl, String secondImageUrl, String firstParagraph, String secondParagraph, String uid, String appLink, String viewType) {
        this.title = title;
        this.dateAndAuthor = dateAndAuthor;
        this.firstImageUrl = firstImageUrl;
        this.secondImageUrl = secondImageUrl;
        this.firstParagraph = firstParagraph;
        this.secondParagraph = secondParagraph;
        this.uid = uid;
        this.appLink = appLink;
        this.viewType = viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAndAuthor() {
        return dateAndAuthor;
    }

    public void setDateAndAuthor(String dateAndAuthor) {
        this.dateAndAuthor = dateAndAuthor;
    }

    public String getFirstImageUrl() {
        return firstImageUrl;
    }

    public void setFirstImageUrl(String firstImageUrl) {
        this.firstImageUrl = firstImageUrl;
    }

    public String getSecondImageUrl() {
        return secondImageUrl;
    }

    public void setSecondImageUrl(String secondImageUrl) {
        this.secondImageUrl = secondImageUrl;
    }

    public String getFirstParagraph() {
        return firstParagraph;
    }

    public void setFirstParagraph(String firstParagraph) {
        this.firstParagraph = firstParagraph;
    }

    public String getSecondParagraph() {
        return secondParagraph;
    }

    public void setSecondParagraph(String secondParagraph) {
        this.secondParagraph = secondParagraph;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
