package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Enemy extends GameObject {
    private Point location;
    private Bitmap bitmapObject;
    //private int objectWidth;
    //private int objectHeight;
    private int speed;
    private int screenHeight;
    private int screenWidth;
    private int S; // small square size
    //private final int SQUARE_SIZE = 50; //create constant size of each square 50 pixels
    Enemy(Context context, Point size) {
        super(size);
        this.S = CONSTANT.SQUARE_SIZE;
        //this.objectWidth = SQUARE_SIZE;
        //this.objectHeight = SQUARE_SIZE;
        this.location = new Point();
        this.speed = 2;
        screenWidth = size.x;
        screenHeight = size.y;
        this.bitmapObject = this.setBitmapObject(context, R.drawable.basic_enemy);
    }
    Bitmap setBitmapObject(Context context, int id){
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmapObject = Bitmap.createScaledBitmap(this.bitmapObject, S, S, false);
        return bitmapObject;
    }
    void draw(Canvas canvas, Paint paint, GameState gameState, Point p){
            canvas.drawBitmap(this.bitmapObject, p.x * S, p.y * S, paint);
    }
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    void move(){
            if(this.location.x<screenWidth/2+500-S){
                this.location.x += speed;
            }else{
                this.location.y += speed;
            }
    }
}
