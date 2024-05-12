package com.example.mathprojectshaharlt;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Card {
    private int id;
    private int idcontent;
    private int imagecard;
    boolean isHide;
    boolean findZoog;


    public Card(int id, int idcontent, int imagecard, boolean isHide, boolean findZoog){
        this.id = id;
        this.idcontent =  idcontent;
        this.imagecard = imagecard;
        this.isHide = isHide;
        this.findZoog = findZoog;
    }

    public Card(int id, int idcontent, int imagecard){
        this.id = id;
        this.idcontent =  idcontent;
        this.imagecard = imagecard;
        this.isHide = true;

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

    public int getIdcontent() {
        return idcontent;
    }

    public void setIdcontent(int idcontent) {
        this.idcontent = idcontent;
    }
}
