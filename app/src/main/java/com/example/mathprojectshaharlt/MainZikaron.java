package com.example.mathprojectshaharlt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;



public class MainZikaron extends AppCompatActivity {
    private RecyclerView rcShowCards;
    private MainVM mainVM;
    CardsAdapter cardsAdapter;
    CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("games");
    String Winner ="";
    Intent intent = new Intent();
    private BatteryRecivever batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zikaron);
        batteryReceiver = new BatteryRecivever();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
        rcShowCards = findViewById(R.id.rcShowCards);
        mainVM = new ViewModelProvider(this).get(MainVM.class);
        String code = getIntent().getStringExtra("code");
        String gameId = getIntent().getStringExtra("gameId");
        int PlayerTurn = getIntent().getIntExtra("PlayerTurn", 0);


        mainVM.setGameCode(code);
        mainVM.setGameId(gameId);
        mainVM.getJson();

        mainVM.isPlayerTurn(PlayerTurn, new MainVM.Callback<Boolean>() {
            @Override
            public void onResult(Boolean isTurn) {
                if (isTurn) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    init();
                }else {
                    init();
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
    }

        public void init(){
        whoWins();
            mainVM.Cards.observe(this, new Observer<ArrayList<Card>>() {
                @Override
                public void onChanged(ArrayList<Card> cards) {
                    cardsAdapter = new CardsAdapter(cards, new CardsAdapter.OnitemClickListener() {
                        @Override
                        public void OnItemClick(Card item) {
                            if (!item.findZoog) {
                                if (opossiteCards(mainVM.Cards.getValue()) == 1 || opossiteCards(mainVM.Cards.getValue()) == 0) {//בודק כמה קלפים הפוכים - נכנס במקרה של 1 או 0
                                    ExposeCard(mainVM.Cards.getValue(), item);//מוצא את הקלף שנלחץ במערך והופך אותו
                                    if (isSeconed(mainVM.Cards.getValue())) {// האם זה הקלף השני שהפוך?
                                        if (isSame(mainVM.Cards.getValue())) {//האם 2 הקלפים ההפוכים דומים?
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    markZoog(mainVM.Cards.getValue());
                                                    cardsAdapter.setCards(mainVM.Cards.getValue());
                                                    cardsAdapter.notifyDataSetChanged();
                                                    mainVM.saveToFirebase();
                                                    mainVM.updateScore();
                                                    mainVM.finishMyTurn();
                                                }
                                            }, 750);
////
                                        } else {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    hideCards(mainVM.Cards.getValue());
                                                    cardsAdapter.setCards(mainVM.Cards.getValue());
                                                    cardsAdapter.notifyDataSetChanged();
                                                    mainVM.saveToFirebase();
                                                    mainVM.finishMyTurn();
                                                }
                                            }, 750);
                                        }
                                    }
                                }
                                cardsAdapter.setCards(mainVM.Cards.getValue());
                                cardsAdapter.notifyDataSetChanged();
                                mainVM.saveToFirebase();

                            }
                        }
                    });
                    rcShowCards.setLayoutManager(new GridLayoutManager(MainZikaron.this, 3));
                    rcShowCards.setAdapter(cardsAdapter);
                    rcShowCards.setHasFixedSize(true);

                }
            });
        }
    public void whoWins(){
        collectionRef.whereEqualTo("gameCode",mainVM.getGameCode()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot dc : queryDocumentSnapshots) {
                        if (dc.getLong("p1Score") + dc.getLong("p2Score") == 6){
                            if (dc.getLong("p1Score") > dc.getLong("p2Score")) {
                                Toast.makeText(MainZikaron.this, "Player 1 wins", Toast.LENGTH_SHORT).show();
                                Winner = "player1";

                            } else if (dc.getLong("p1Score") < dc.getLong("p2Score")) {
                                Toast.makeText(MainZikaron.this, "Player 2 wins", Toast.LENGTH_SHORT).show();
                                Winner = "player2";
                            } else {
                                Toast.makeText(MainZikaron.this, "its a draw", Toast.LENGTH_SHORT).show();
                                Winner = "draw";
                            }
                            intent.putExtra("winner",Winner);
                            setResult(RESULT_OK,intent);
                            finish();
                    }
                    }
            }
        });
    }
//    public boolean isGameFinshed(ArrayList<Card>cards){
//        int counter =0;
//        for (int i=0; i<cards.size();i++){
//            if (cards.get(i).findZoog==true){
//                counter++;
//            }
//        }
//        if (counter == 11){
//            return true;
//        }else {
//            return false;
//        }
//    }
    public void markZoog(ArrayList<Card>cards){
        int counter = 0;
        for (int i =0;i<cards.size();i++){
            if(cards.get(i).isHide == false){
                cards.get(i).findZoog = true;
                cards.get(i).isHide=true;
            }
        }
    }
    public int opossiteCards(ArrayList<Card>cards){
        int counter = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isHide == false)
                counter++;
        }
    return counter;
    }

    public boolean isSeconed(ArrayList<Card>cards){
        int counter = 0;
        for (int i =0 ; i< cards.size();i++){
            if (cards.get(i).isHide == false)
                counter++;
        }
        if(counter == 2)
            return true;
        return false;
    }

    public void ExposeCard(ArrayList<Card> cards, Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (card.getId() == cards.get(i).getId()) {
                cards.get(i).isHide = false;

            }
        }
    }
    public void hideCards(ArrayList<Card> cards){
        for (int i = 0;i<cards.size();i++){
            if (cards.get(i).isHide == false){
                cards.get(i).isHide = true;
            }
        }
    }

    public boolean isSame(ArrayList<Card> cards) {
        int tmp = -1;
        int count = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).isHide == false) {
                count++;
                if (count == 2) {
                    if (cards.get(i).getIdcontent() == tmp) {
                        return true;
                    }
                    return false;
                } else if(count ==1) {
                    tmp = cards.get(i).getIdcontent();
                }

            }

        }
        return false;
    }


}