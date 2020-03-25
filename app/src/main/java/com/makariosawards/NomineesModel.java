package com.makariosawards;

public class NomineesModel {

    String age, directiva, drama, firstName, fullName, lastName, music, nationality, nomineeDescription, pictureUrl, poetry, uid;

    public NomineesModel() {
    }

    public NomineesModel(String age, String directiva, String drama, String firstName, String fullName, String lastName, String music, String nationality, String nomineeDescription, String pictureUrl, String poetry, String uid) {
        this.age = age;
        this.directiva = directiva;
        this.drama = drama;
        this.firstName = firstName;
        this.fullName = fullName;
        this.lastName = lastName;
        this.music = music;
        this.nationality = nationality;
        this.nomineeDescription = nomineeDescription;
        this.pictureUrl = pictureUrl;
        this.poetry = poetry;
        this.uid = uid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDirectiva() {
        return directiva;
    }

    public void setDirectiva(String directiva) {
        this.directiva = directiva;
    }

    public String getDrama() {
        return drama;
    }

    public void setDrama(String drama) {
        this.drama = drama;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNomineeDescription() {
        return nomineeDescription;
    }

    public void setNomineeDescription(String nomineeDescription) {
        this.nomineeDescription = nomineeDescription;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPoetry() {
        return poetry;
    }

    public void setPoetry(String poetry) {
        this.poetry = poetry;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
