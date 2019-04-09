package com.makariosawards;

public class NomineesModel {

    String firstName, lastName, pictureUrl, nomineeDescription, group;
    String uid;

    public NomineesModel() {
    }

    public NomineesModel(String firstName, String lastName, String pictureUrl, String nomineeDescription, String group, String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureUrl = pictureUrl;
        this.nomineeDescription = nomineeDescription;
        this.group = group;
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getNomineeDescription() {
        return nomineeDescription;
    }

    public void setNomineeDescription(String nomineeDescription) {
        this.nomineeDescription = nomineeDescription;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
