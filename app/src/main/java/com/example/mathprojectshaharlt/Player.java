package com.example.mathprojectshaharlt;

public class Player {
    private String email;
    private int wins;

    public Player(String email , int wins){
        wins = this.wins;
        email = this.email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
