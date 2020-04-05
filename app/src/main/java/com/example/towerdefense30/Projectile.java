package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

class Projectile extends GameObject{
    private Bitmap bitmapObject;
    private int objectWidth;
    private int objectHeight;
    private Point location;
    private int speed;
    private Rect collider;


    Projectile(Context context, Point size, Tower tower){
        super(context, size);
        objectWidth = size.x/50;
        objectHeight = size.y/50;
        location = new Point(tower.getLocationX()+CONSTANT.SQUARE_SIZE/2, tower.getLocationY()+CONSTANT.SQUARE_SIZE/2); //the initial location would the center of the tower
        this.bitmapObject = setBitmapObject(context);
        speed = 1;
        collider = new Rect(location.x+objectWidth/10, location.y+objectHeight/10, location.x+objectWidth+objectWidth/10, location.y+objectHeight+objectHeight/10);
    }
    private Bitmap setBitmapObject(Context context){
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), R.drawable.projectile_arrow);
        this.bitmapObject= Bitmap.createScaledBitmap(this.bitmapObject, objectWidth, objectHeight, false);
        return bitmapObject;
    }
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmapObject, location.x, location.y, paint);
    }
    void move(Enemy enemy, Tower tower){

        if(enemy.getLocationX() < tower.getLocationX()){
            location.x -= enemy.getLocationX();
        }
        else{
            location.x += enemy.getLocationX();
        }
        if(enemy.getLocationY() < tower.getLocationY()){
            location.y -= enemy.getLocationY();
        }
        else{
            location.y += enemy.getLocationY();;
        }
    }
    void pause(){

        speed=0;
    }
    int getLocationX(){
        return location.x;
    }
    int getLocationY(){
        return location.y;
    }
}
