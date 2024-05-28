package com.example.mathprojectshaharlt;

public class Game {
    private String gameCode;
    private String player1;
    private String player2;
    private int status;

    public Game(String gameCode,String player1,String player2,int status){
        this.gameCode=gameCode;
        this.player1 = player1;
        this.player2 =player2;
        this.status = status;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
