package com.example.mathprojectshaharlt;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainVM extends ViewModel{
    private String arrJson;
    String gameCode;
    String gameId;
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
//    public void isPlayerTurn(int playerTurn, Callback<Boolean> callback) {
//        if (collectionRef == null || gameCode == null || gameCode.isEmpty()) {
//            callback.onResult(false);
//            return;
//        }
//
//        collectionRef.whereEqualTo("gameCode", gameCode).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    QuerySnapshot querySnapshot = task.getResult();
//                    if (!querySnapshot.isEmpty()) {
//                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
//                        Long currentTurnLong = documentSnapshot.getLong("turn");
//                        if (currentTurnLong != null) {
//                            long currentTurn = currentTurnLong;
//                            boolean isTurn = (currentTurn == playerTurn);
//                            callback.onResult(isTurn);
//                        } else {
//                            callback.onResult(false);
//                        }
//                    } else {
//                        callback.onResult(false);
//                    }
//                } else {
//                    callback.onResult(false);
//                }
//            }
//        });
//    }
public void isPlayerTurn(int playerTurn, Callback<Boolean> callback) {
    if (collectionRef == null || gameCode == null || gameCode.isEmpty()) {
        callback.onResult(false);
        return;
    }

    collectionRef.whereEqualTo("gameCode", gameCode)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        callback.onResult(false);
                        return;
                    }

                    if (!snapshots.isEmpty()) {
                        DocumentSnapshot documentSnapshot = snapshots.getDocuments().get(0);
                        Long currentTurnLong = documentSnapshot.getLong("turn");
                        if (currentTurnLong != null) {
                            long currentTurn = currentTurnLong;
                            boolean isTurn = (currentTurn == playerTurn);
                            callback.onResult(isTurn);
                        } else {
                            callback.onResult(false);
                        }
                    } else {
                        callback.onResult(false);
                    }
                }
            });
}

    public void finishMyTurn(){
        collectionRef.whereEqualTo("gameCode",gameCode).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            for (DocumentSnapshot dc : queryDocumentSnapshots){
                if (dc.getLong("turn")== 1) {
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("turn", 2);
                    collectionRef.document(gameId).update(updates);
                } else if (dc.getLong("turn")==2) {
                    Map<String, Object> updates = new HashMap<>();
                    updates.put("turn", 1);
                    collectionRef.document(gameId).update(updates);
                }
                }
            }
        });
    }
    public void updateScore(){
        collectionRef.whereEqualTo("gameCode",gameCode).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dc : queryDocumentSnapshots){
                    if (dc.getLong("turn")== 1) {
                        Long score = dc.getLong("p1Score");
                        score = score+1;
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("p1Score", score);
                        collectionRef.document(gameId).update(updates);
                    } else if (dc.getLong("turn")==2) {
                        Long score = dc.getLong("p2Score");
                        score = score+1;
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("p2Score", score);
                        collectionRef.document(gameId).update(updates);
                    }
                }
            }
        });
    }


    // ממשק Callback
    interface Callback<T> {
        void onResult(T result);
    }
    public void finishGame(){

    }


//    public boolean isPlayerTurn(int Pturn){
//        boolean f = false;
//        collectionRef.whereEqualTo("gameCode",gameCode).addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                long turn = documentSnapshot.getLong("turn");
//                if (turn == Pturn){
//                    f = true;
//                }
//            }
//        });
//        return f;
//    }
//public void isPlayerTurn(int playerTurn, OnTurnCheckedListener listener) {
//    if (collectionRef == null || gameCode == null || gameCode.isEmpty()) {
//        listener.onTurnChecked(false);
//        return;
//    }
//    collectionRef.whereEqualTo("gameCode", gameCode).get()
//            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        QuerySnapshot querySnapshot = task.getResult();
//                        if (!querySnapshot.isEmpty()) {
//                            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
//                            long currentTurn = documentSnapshot.getLong("turn");
//                            boolean isTurn = (currentTurn == playerTurn);
//                            listener.onTurnChecked(isTurn);
//                        } else {
//                            // המסמך לא נמצא
//                            listener.onTurnChecked(false);
//                        }
//                    } else {
//                        // טיפול בשגיאה
//                        listener.onTurnChecked(false);
//                    }
//                }
//            });
//}
//
//    // ממשק הקשבה לבדיקת התור
//    public interface OnTurnCheckedListener {
//        void onTurnChecked(boolean isTurn);
//    }





    public void saveToFirebase() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("jsonCards", Cards.getValue());
        collectionRef.document(gameId).update(updates).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("Firestore", "Game updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Firestore", "Error updating game: " + e.getMessage());
                    }
                });
    }

    public void getJson(){
        collectionRef.whereEqualTo("gameCode",gameCode).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Game game = documentSnapshot.toObject(Game.class);
                            Cards.setValue(game.getJsonCards());
                }
            }
        });
//        collectionRef.whereEqualTo("gameCode",gameCode).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                //if (!queryDocumentSnapshots.isEmpty()){
//                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                        //if (documentSnapshot.exists()) {
////                            arrJson = documentSnapshot.getString("cards");
////                            ArrayList<Card>JCards = gson.fromJson(arrJson, ArrayList.class);
////                            JsonToArr();
////                            List<Card> cardList = (List<Card>) documentSnapshot.get("jsonCards");
////                            if (cardList != null) {
////                                ArrayList<Card> jsonCards = new ArrayList<>(cardList);
////                                int a = 0;
////                                Cards.setValue(jsonCards);
//                                // Use the cardList as needed
////                            }
//
//                            Game game = documentSnapshot.toObject(Game.class);
//                            Cards.setValue(game.getJsonCards());
//                        //}
//                    }
//            //}
//            }
//        });
    }
    public  void JsonToArr(){
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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}

