package com.example.mathprojectshaharlt;

import java.util.ArrayList;

public class Game {
    private String gameCode;
    private String player1;
    private String player2;
    private int status;
    private int turn;
    private String cards;

    public ArrayList<Card> getJsonCards() {
        return jsonCards;
    }

    public void setJsonCards(ArrayList<Card> jsonCards) {
        this.jsonCards = jsonCards;
    }

    private ArrayList<Card> jsonCards;

    public Game() {}


//    public Game(String gameCode,String player1,String player2,int status,String cards){
//        this.gameCode=gameCode;
//        this.player1 = player1;
//        this.player2 =player2;
//        this.status = status;
//        this.cards = cards;
//        this.jsonCards = new ArrayList<>();
//    }

    public Game(String gameCode,String player1,String player2,int status,int turn, ArrayList<Card> jsonCards){
        this.gameCode=gameCode;
        this.player1 = player1;
        this.player2 =player2;
        this.status = status;
        this.turn = turn;
        this.jsonCards = jsonCards;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
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
