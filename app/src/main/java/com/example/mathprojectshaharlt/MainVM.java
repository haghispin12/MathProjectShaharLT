package com.example.mathprojectshaharlt;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.sql.Array;
import java.util.ArrayList;

public class MainVM extends ViewModel{
    MutableLiveData<ArrayList<Card>> Cards;
    public MainVM(){
        Cards = new MutableLiveData<>();
        ArrayList<Card> ECards = new ArrayList<Card>();
        ECards.add(new Card(1,R.drawable.hyunday));
        ECards.add(new Card(1, R.drawable.hyundaaycar));
        ECards.add(new Card(2,R.drawable.logo_mercedes_img_1));
        ECards.add(new Card(2,R.drawable.mercedescar));
        ECards.add(new Card(3,R.drawable.mazdalogo));
        ECards.add(new Card(3,R.drawable.mzadacar));
        ECards.add(new Card(4,R.drawable.bmwcar));
        ECards.add(new Card(4,R.drawable.bmwLogo));
        ECards.add(new Card(5,R.drawable.toyotacar));
        ECards.add(new Card(5,R.drawable.toyotalogo));
        Cards.setValue(ECards);
    }


}

