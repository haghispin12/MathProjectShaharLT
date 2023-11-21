package com.example.mathprojectshaharlt;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewMoudle extends ViewModel {
    MutableLiveData<Integer> LDnum1;
    MutableLiveData<Integer> LDnum2;
    Exercise exercise;

    public MainViewMoudle(){
    LDnum1 = new MutableLiveData<>();
    LDnum2 = new MutableLiveData<>();
    exercise = new Exercise();
    }
    public void challenge(){
        exercise.challenge();
        LDnum2.setValue(exercise.getNum1());
        LDnum1.setValue(exercise.getNum());
    }
    public void untill20(){
        exercise.until20();
        LDnum2.setValue(exercise.getNum1());
        LDnum1.setValue(exercise.getNum());
    }
    public void multyTable(){
        exercise.multyTable();
        LDnum2.setValue(exercise.getNum1());
        LDnum1.setValue(exercise.getNum());
    }


}
