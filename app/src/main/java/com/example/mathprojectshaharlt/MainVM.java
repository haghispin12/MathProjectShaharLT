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
        ECards.add(new Card(1, 1, R.drawable.hyunday,true));
        ECards.add(new Card(2, 1,R.drawable.hyundaaycar,true));
        ECards.add(new Card(3,2,R.drawable.logo_mercedes_img_1,true));
        ECards.add(new Card(4,2,R.drawable.mercedescar,true));
        ECards.add(new Card(5,3,R.drawable.mazdalogo,true));
        ECards.add(new Card(6,3,R.drawable.mzadacar,true));
        ECards.add(new Card(7,4,R.drawable.bmwcar,true));
        ECards.add(new Card(8,4,R.drawable.bmw,true));
        ECards.add(new Card(9,5,R.drawable.toyotacar,true));
        ECards.add(new Card(10,5,R.drawable.toyotalogo,true));
        ECards.add(new Card(11,6,R.drawable.bydlogo,true));
        ECards.add(new Card(12,6,R.drawable.bydcar,true));
        Cards.setValue(ECards);
    }
}

