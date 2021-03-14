package com.example.platformer;

import android.graphics.Bitmap;

public class Player extends Character{


    protected Player(Bitmap image, int x, int y, int v) {
        //Character(Bitmap image, int colCount, int rowCount, int x, int y, int v, int jumpHeight, int jumpDuration, int life)
        super(image, x, y, v);
    }


}
