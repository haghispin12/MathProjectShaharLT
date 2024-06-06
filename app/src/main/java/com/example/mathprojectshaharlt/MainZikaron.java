package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainZikaron extends AppCompatActivity {
    private RecyclerView rcShowCards;
    private MainVM mainVM;
    CardsAdapter cardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zikaron);
        rcShowCards = findViewById(R.id.rcShowCards);
        mainVM = new ViewModelProvider(this).get(MainVM.class);
        String code = getIntent().getStringExtra("code");
        String gameId = getIntent().getStringExtra("gameId");

//        SharedPreferences sharedPreferences = getSharedPreferences("code", MODE_PRIVATE);
//        String code = sharedPreferences.getString("code", "default_value");

        mainVM.setGameCode(code);
        mainVM.setGameId(gameId);
        mainVM.getJson();

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