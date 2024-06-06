package com.example.mathprojectshaharlt;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainVM extends ViewModel{
    private String arrJson;
    String gameCode;
    MutableLiveData<ArrayList<Card>> Cards;
    MutableLiveData<Integer>exposed;
    Gson gson = new Gson();
    CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("games");
    public MainVM() {
        Cards = new MutableLiveData<>();
        exposed = new MutableLiveData<>(0);
        ArrayList<Card> ECards = new ArrayList<Card>();
        ECards.add(new Card(1, 1, R.drawable.hyunday, true, false));
        ECards.add(new Card(2, 1, R.drawable.hyundaaycar, true, false));
        ECards.add(new Card(3, 2, R.drawable.logo_mercedes_img_1, true, false));
        ECards.add(new Card(4, 2, R.drawable.mercedescar, true, false));
        ECards.add(new Card(5, 3, R.drawable.mazdalogo, true, false));
        ECards.add(new Card(6, 3, R.drawable.mzadacar, true, false));
        ECards.add(new Card(7, 4, R.drawable.bmwcar, true, false));
        ECards.add(new Card(8, 4, R.drawable.bmw, true, false));
        ECards.add(new Card(9, 5, R.drawable.toyotacar, true, false));
        ECards.add(new Card(10, 5, R.drawable.toyotalogo, true, false));
        ECards.add(new Card(11, 6, R.drawable.bydlogo, true, false));
        ECards.add(new Card(12, 6, R.drawable.bydcar, true, false));
        Cards.setValue(ECards);


    }
    public void getJson(){
        collectionRef.whereEqualTo("gameCode",gameCode).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    if(documentSnapshot.exists()){
                        arrJson = documentSnapshot.getString("cards");
                        JsonToArr();
                    }
                }
            }
        });
    }
    public void JsonToArr(){
    ArrayList<Card>JCards = gson.fromJson(arrJson, ArrayList.class);
    Cards.setValue(JCards);
    }
    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public MutableLiveData<Integer> getExposed() {
        return exposed;
    }

    public void setExposed(MutableLiveData<Integer> exposed) {
        this.exposed = exposed;
    }

    public MutableLiveData<ArrayList<Card>> getCards() {
        return Cards;
    }

    public void setCards(MutableLiveData<ArrayList<Card>> cards) {
        Cards = cards;
    }
}

