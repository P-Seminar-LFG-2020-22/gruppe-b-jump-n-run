package com.example.platformer;

import android.graphics.Bitmap;

public class Enemy extends Character {


    protected Enemy(Bitmap image, int x, int y, int v) {
        //Character(Bitmap image, int colCount, int rowCount, int x, int y, int v, int jumpHeight, int jumpDuration, int life)
        super(image, x, y, v);
    }
}
