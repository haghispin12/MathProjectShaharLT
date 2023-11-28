package com.example.mathprojectshaharlt;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewMoudle extends ViewModel {
    MutableLiveData<Integer> LDnum1;
    MutableLiveData<Integer> LDnum2;
    Exercise exercise;
    User user;
    int lastScore;

    public MainViewMoudle(){
    LDnum1 = new MutableLiveData<>();
    LDnum2 = new MutableLiveData<>();
    exercise = new Exercise();
    user = new User();
    }

    public int getLastScore() {
        return lastScore;
    }

    public void challenge(){
        exercise.challenge();
        lastScore = 20;
        LDnum2.setValue(exercise.getNum1());
        LDnum1.setValue(exercise.getNum());
    }
    public void untill20(){
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





}
