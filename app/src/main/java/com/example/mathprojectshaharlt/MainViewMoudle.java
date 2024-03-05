package com.example.mathprojectshaharlt;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewMoudle extends ViewModel {
    MutableLiveData<Integer> LDnum1;
    MutableLiveData<Integer> LDnum2;
    MutableLiveData<ArrayList<User>> users;
    Exercise exercise;
    User user;
    String name;
    int lastScore;

    public MainViewMoudle(){
    LDnum1 = new MutableLiveData<>();
    LDnum2 = new MutableLiveData<>();
    users = new MutableLiveData<>();
    exercise = new Exercise();

    }

    public int getLastScore() {
        return lastScore;
    }
    public void setRating(int Rate){
        user.setRating(Rate);
    }
    public void challenge(){
        exercise.challenge();
        lastScore = 20;
        LDnum2.setValue(exercise.getNum1());
        LDnum1.setValue(exercise.getNum());
    }
    public void until20(){
        exercise.until20();
        lastScore = 10;
        LDnum2.setValue(exercise.getNum1());
        LDnum1.setValue(exercise.getNum());
    }
    public void multyTable(){
        exercise.multyTable();
        lastScore = 5;
        LDnum2.setValue(exercise.getNum1());
        LDnum1.setValue(exercise.getNum());
    }
    public boolean check(int ans){
        if( exercise.check(ans))
            return true;
        return false;

    }

    public void setName(String name) {
        this.name = name;
        user=new User(name);
    }

    public Exercise getExercise() {
        return exercise;
    }

    public User getUser() {
        return user;
    }

    public MutableLiveData<Integer> getLDnum1() {
        return LDnum1;
    }

    public MutableLiveData<Integer> getLDnum2() {
        return LDnum2;
    }


    public void setUser(User user) {
        this.user = user;
    }
    public void setScore(int Score){
        user.setScore(lastScore);
    }

    public void addUserDatabase(Context context){
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.insert(user,context);
        ArrayList<User> users = dbHelper.selectAll();
        int n =0;
    }
    public void setUserImg(Uri uri){
        user.setUri(uri);
    }
    public void selectAll(Context context){
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.selectAll();
    }
}
