package com.makariosawards;

public class HomeDataModel {

    String title, dateAndAuthor;
    String primaryImageUrl, secondaryImageUrl;
    String paragraph1, paragraph2, paragraph3, paragraph4;
    String paragraph5, paragraph6, paragraph7, paragraph8;
    String uid;
    String appLink;
    String viewType;

    public HomeDataModel() {}

    public HomeDataModel(String title, String dateAndAuthor, String primaryImageUrl, String secondaryImageUrl, String paragraph1, String paragraph2, String paragraph3, String paragraph4, String paragraph5, String paragraph6, String paragraph7, String paragraph8, String uid, String appLink, String viewType) {
        this.title = title;
        this.dateAndAuthor = dateAndAuthor;
        this.primaryImageUrl = primaryImageUrl;
        this.secondaryImageUrl = secondaryImageUrl;
        this.paragraph1 = paragraph1;
        this.paragraph2 = paragraph2;
        this.paragraph3 = paragraph3;
        this.paragraph4 = paragraph4;
        this.paragraph5 = paragraph5;
        this.paragraph6 = paragraph6;
        this.paragraph7 = paragraph7;
        this.paragraph8 = paragraph8;
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

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }

    public String getSecondaryImageUrl() {
        return secondaryImageUrl;
    }

    public void setSecondaryImageUrl(String secondaryImageUrl) {
        this.secondaryImageUrl = secondaryImageUrl;
    }

    public String getParagraph1() {
        return paragraph1;
    }

    public void setParagraph1(String paragraph1) {
        this.paragraph1 = paragraph1;
    }

    public String getParagraph2() {
        return paragraph2;
    }

    public void setParagraph2(String paragraph2) {
        this.paragraph2 = paragraph2;
    }

    public String getParagraph3() {
        return paragraph3;
    }

    public void setParagraph3(String paragraph3) {
        this.paragraph3 = paragraph3;
    }

    public String getParagraph4() {
        return paragraph4;
    }

    public void setParagraph4(String paragraph4) {
        this.paragraph4 = paragraph4;
    }

    public String getParagraph5() {
        return paragraph5;
    }

    public void setParagraph5(String paragraph5) {
        this.paragraph5 = paragraph5;
    }

    public String getParagraph6() {
        return paragraph6;
    }

    public void setParagraph6(String paragraph6) {
        this.paragraph6 = paragraph6;
    }

    public String getParagraph7() {
        return paragraph7;
    }

    public void setParagraph7(String paragraph7) {
        this.paragraph7 = paragraph7;
    }

    public String getParagraph8() {
        return paragraph8;
    }

    public void setParagraph8(String paragraph8) {
        this.paragraph8 = paragraph8;
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
