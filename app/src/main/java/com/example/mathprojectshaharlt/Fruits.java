package com.example.mathprojectshaharlt;

import android.graphics.drawable.Drawable;

public class Fruits {
    private String name;
    private int drawable;

    public Fruits(String name,int drawable) {
        this.name = name;
        this.drawable = drawable;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
