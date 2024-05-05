package com.example.mathprojectshaharlt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

public class MainZikaron extends AppCompatActivity {
    private RecyclerView rcShowCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zikaron);
        rcShowCards = findViewById(R.id.rcShowCards);

    }
}