package com.example.mathprojectshaharlt;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Card {
    private int id;
    private int imagecard;


    public Card(int id, int imagecard){
        this.id = id;
        this.imagecard = imagecard;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagecard() {
        return imagecard;
    }

    public void setImagecard(int imagecard) {
        this.imagecard = imagecard;
    }
}
