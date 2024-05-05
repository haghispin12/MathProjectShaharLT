package com.example.mathprojectshaharlt;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Card {
    private int id;
    private Bitmap cardbitmap;


    public Card(int id,Bitmap cardbitmap){
        this.id = id;
        this.cardbitmap = cardbitmap;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getCardbitmap() {
        return cardbitmap;
    }

    public void setCardbitmap(Bitmap cardbitmap) {
        this.cardbitmap = cardbitmap;
    }
}
