package com.example.platformer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Character {

    //Bildquelle des Objekts
    protected Bitmap image;

    //x,y Koordinaten des Objekts
    protected int x, y;

    //Breite, Höhe des Objekts
    protected final int width, height;

    //Geschwindigkeit des Objekts in x-Richtung, Einheit:pixel/millisekunde= 1000 pixel/sekunde
    protected float v;

    //Richtung des Objekts; direction=1 nach rechts, direction=-1 nach links
    protected int direction;

    //Konstruktor; setzt Attribute = Parameter
    protected Character(Bitmap image, int x, int y, float v) {
        this.image = image;

        this.x = x;
        this.y = y;

        this.width = image.getWidth();
        this.height = image.getHeight();

        this.v = v;

    }
    // gibt Bitmap von image zurück
    protected Bitmap createSubImageAt(int x, int y) {
        Bitmap subImage = Bitmap.createBitmap(image, x, y, width, height);
        return subImage;
    }

    //Methode, um Objekt um v in x-Richtung zu bewegen; direction=1 nach rechts, direction=-1 nach links
    protected void move() {
        switch (getDirection()) {
            case 1: x+=v; break;
            case -1: x-=v; break;
        }
    }

    //Sprung
    protected void jump(){
        int startX = x;
        int startY = y;
    }


    //setter
    public void setDirection(int direction) {
        this.direction = direction;
    }

    //getter
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public float getV() {
        return v;
    }

    public int getDirection() {
        return direction;
    }
}
