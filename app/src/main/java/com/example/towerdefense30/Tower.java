package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Tower extends GameObject {
    private Point location;
    private Bitmap bitmapObject;
    //private int objectWidth;
    //private int objectHeight;
    private int S; // Small square size
    //private final int SQUARE_SIZE = 50; //create constant size of each square 50 pixels

    Tower(Context context, Point size) {
        super(size);
        this.S = CONSTANT.SQUARE_SIZE;
        //this.objectWidth = SQUARE_SIZE * 2;
        //this.objectHeight = SQUARE_SIZE * 2;
        this.bitmapObject = this.setBitmapObject(context, R.drawable.tower);
        this.location = new Point();

    }
    Bitmap setBitmapObject(Context context, int id){
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmapObject = Bitmap.createScaledBitmap(this.bitmapObject, S, S, false);
        return bitmapObject;
    }
    void draw(Canvas canvas, Paint paint, GameState gameState, Point location){
        canvas.drawBitmap(this.bitmapObject, location.x , location.y, paint);
    }
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
}
