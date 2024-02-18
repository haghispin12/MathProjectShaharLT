package com.example.mathprojectshaharlt;

import android.net.Uri;

public class User {
    private String UserName;
    private int score;
    long Id;
    private int rating;
    private Uri uri;

    public User(String UserName, int score, long id) {
        UserName = this.UserName;
        score = this.score;
        id = this.Id;
    }
    public User(String UserName){
        this.UserName=UserName;
    }
    public void setUri(Uri uri){
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUsarName(String userName) {
        UserName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }


}

