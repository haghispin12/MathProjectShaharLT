package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainZikaron extends AppCompatActivity {
    private RecyclerView rcShowCards;
    private MainVM mainVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zikaron);
        rcShowCards = findViewById(R.id.rcShowCards);
        mainVM = new ViewModelProvider(this).get(MainVM.class);


        mainVM.Cards.observe(this, new Observer<ArrayList<Card>>() {
            @Override
            public void onChanged(ArrayList<Card> cards) {
                CardsAdapter cardsAdapter = new CardsAdapter(cards, new CardsAdapter.OnitemClickListener() {
                    @Override
                    public void OnItemClick(Card item) {

                    }
                });
                rcShowCards.setLayoutManager(new LinearLayoutManager(MainZikaron.this));
                rcShowCards.setAdapter(cardsAdapter);
                rcShowCards.setHasFixedSize(true);

            }
        });
    }
}