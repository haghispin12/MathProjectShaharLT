package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
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
        mainVM.exposed.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 2) {
                    if(isSame(mainVM.Cards.getValue())) {
                        mainVM.exposed.setValue(0);
                        Toast.makeText(MainZikaron.this, "great", Toast.LENGTH_LONG).show();
                    }
                    mainVM.exposed.setValue(0);
                    Toast.makeText(MainZikaron.this, "not great", Toast.LENGTH_LONG).show();
                    }
            }
        });


        mainVM.Cards.observe(this, new Observer<ArrayList<Card>>() {
            @Override
            public void onChanged(ArrayList<Card> cards) {
                cardsAdapter = new CardsAdapter(cards, new CardsAdapter.OnitemClickListener() {
                    @Override
                    public void OnItemClick(Card item) {
                        ExposeCard(cards, item);
                        mainVM.exposed.setValue(mainVM.getExposed().getValue() + 1);
                        cardsAdapter.setCards(cards);
                        cardsAdapter.notifyDataSetChanged();
                    }
                });
                rcShowCards.setLayoutManager(new GridLayoutManager(MainZikaron.this, 3));
                rcShowCards.setAdapter(cardsAdapter);
                rcShowCards.setHasFixedSize(true);

            }
        });
    }

    public void ExposeCard(ArrayList<Card> cards, Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (card.getId() == cards.get(i).getId()) {
                cards.get(i).isHide = false;

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