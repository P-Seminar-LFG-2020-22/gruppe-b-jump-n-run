//Quelle für das Meiste: https://codestory.de/10521/anleitung-zum-android-2d-game-fuer-anfaenger

package com.example.platformer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Character {

    //Bildquelle des Objekts
    protected Bitmap image;

    //Anzahl der Reihen/Spalten
    protected int colCount, rowCount;

    //index der Reihe in dem image
    private static final int ROW_RIGHT_TO_LEFT = 0;
    private static final int ROW_LEFT_TO_RIGHT = 1;

    //Reihe/Spalte die benutzt wird
    private int rowUsing, colUsing;

    private final Bitmap[] leftToRight;
    private final Bitmap[] rightToLeft;

    //x,y Koordinaten des Objekts
    protected int x, y;

    //Breite, Höhe des Bildes
    protected final int WIDTH = image.getWidth();
    protected final int HEIGHT = image.getHeight();

    //Breite, Höhe des Objekts
    protected final int width, height;

    //Geschwindigkeit des Objekts in x-Richtung in pixel
    protected int v;

    //wie hoch/lang (in s) das Objekt springen kann
    protected int jumpHeight, jumpDuration;

    //für update()
    protected boolean jumping = false;

    //counter für die Sprung-Methode
    protected int jumpCounter;

    // y-Koordinate am Anfang des Srungs
    protected int startY;

    //Richtung des Objekts; direction=1 nach rechts, direction=-1 nach links
    protected int direction;

    //Anzahl der Leben
    protected int life;

    //für update()
    private long lastDrawNanoTime = -1;


    //Konstruktor; setzt Attribute = Parameter
    protected Character(Bitmap image, int colCount, int rowCount, int x, int y, int v, int jumpHeight, int jumpDuration, int life) {
        this.image = image;

        this.colCount = colCount;
        this.rowCount = rowCount;

        //Initialisierung
        this.rightToLeft = new Bitmap[colCount];
        this.leftToRight = new Bitmap[colCount];

        //Zuweisung der Teilbilder von image
        for (int i=0; i< this.colCount; i++) {
            this.rightToLeft[i] = this.createSubImageAt(ROW_RIGHT_TO_LEFT, i);
            this.leftToRight[i] = this.createSubImageAt(ROW_LEFT_TO_RIGHT, i);
        }

        this.x = x;
        this.y = y;

        this.width = WIDTH/colCount;
        this.height = HEIGHT/rowCount;

        this.v = v;

        this.jumpHeight = jumpHeight;
        this.jumpDuration = jumpDuration;

        this.life = life;
    }

    // gibt Bitmap von image zurück
    protected Bitmap createSubImageAt(int row, int col) {
        //createBitmap(bitmap, x, y, width, height)
        return Bitmap.createBitmap(image, col*width, row*height, width, height);
    }


    //Methode, um Objekt um v in x-Richtung zu bewegen; direction=1 nach rechts, direction=-1 nach links
    protected void move() {
        switch (getDirection()) {
            case 1: x+=v; break;
            case -1: x-=v; break;
        }
    }

    //Sprung mit konstanter Geschwindigkeit bei 60FPS
    protected void jump(int dauer){
        if (jumping) {
            jumpCounter++;
            y += getYdirection() * jumpHeight * dauer/(2*60);
        }
        else {
            jumpCounter=0;
        }
    }

    //aktualisiert das Objekt
    protected void update() {
        //damit eine Animation entsteht
        this.colUsing++;
        if(colUsing>=this.colCount)
            this.colUsing=0;


        long now = System.nanoTime();

        if(lastDrawNanoTime==-1)
            lastDrawNanoTime=now;


        this.move();
        this.jump(jumpDuration);

        //rowUsing
        if (direction>0)
            this.rowUsing = ROW_LEFT_TO_RIGHT;
        else
            this.rowUsing = ROW_RIGHT_TO_LEFT;
    }

    //zeichnet das Objekt
    protected void draw(Canvas canvas) {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap,x, y, null);
        // Last draw time.
        this.lastDrawNanoTime= System.nanoTime();
    }



    //setter
    public void setDirection(int direction) {
        this.direction = direction;
    }

    //getter
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getStartY(){
        if (jumpCounter == 0)
            startY = y;
        return startY;
    }

    public int getMaxY(){
        return getStartY()+jumpHeight;
    }

    public int getYdirection(){
        if (y<getMaxY())
            return 1;
        else
            return -1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getV() {
        return v;
    }

    public int getDirection() {
        return direction;
    }

    public Bitmap[] getMoveBitmaps()  {
        switch (rowUsing)  {
            case ROW_LEFT_TO_RIGHT:
                return this.leftToRight;
            case ROW_RIGHT_TO_LEFT:
                return this.rightToLeft;
            default:
                return null;
        }
    }

    public Bitmap getCurrentMoveBitmap()  {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }
}
