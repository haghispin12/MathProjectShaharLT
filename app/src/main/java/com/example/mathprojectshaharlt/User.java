package com.example.mathprojectshaharlt;

public class User {
    private String UsarName;
    private int score;

    public User(String Username){
    this.UsarName = Username;
    }

    public String getUsarName() {
        return UsarName;
    }

    public void setUsarName(String usarName) {
        UsarName = usarName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
