package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

class Enemy extends GameObject {
    private Bitmap bitmapObject;
    private Bitmap bitmapObjectUp;
    private Bitmap bitmapObjectR;
    private Bitmap bitmapObjectD;
    private Bitmap bitmapObjectDead;
    private int objectWidth;
    private int objectHeight;
    private int screenWidth;
    private int screenHeight;
    private Point location;
    private int speed;
    private static int hitPoint;
    private boolean isDead;
    private Rect rect;
    Enemy(Context context, Point size) {
        super(context);
        objectWidth = CONSTANT.SQUARE_SIZE;
        objectHeight = CONSTANT.SQUARE_SIZE;
        screenWidth = size.x;
        screenHeight = size.y;
        int up = -90;
        int down = 90;
        this.bitmapObject = setBitmapObject(context, objectWidth, objectHeight, R.drawable.basic_enemy);
        this.bitmapObjectUp = rotateBitmap(bitmapObject, up);
        this.bitmapObjectR = setBitmapObject(context, objectWidth, objectHeight, R.drawable.basic_enemy);
        this.bitmapObjectD = rotateBitmap(bitmapObject, down);
        this.bitmapObjectDead = setBitmapObject(context, objectWidth, objectHeight, R.drawable.basic_enemy_dead);
        speed = 1;
        location = new Point();
        hitPoint =10;
        isDead = false;
        rect = new Rect(location.x, location.y, objectWidth, objectHeight);
    }

    private Bitmap rotateBitmap(Bitmap bitmapObject, int degree){
        Matrix matrix = new Matrix();
        matrix.preScale(1, 1);
        matrix.preRotate(degree);
        this.bitmapObject = Bitmap.createBitmap(bitmapObject,0,0, objectWidth, objectHeight, matrix, true);
        return this.bitmapObject;
    }

    private void turnUp(){this.bitmapObject = this.bitmapObjectUp;}
    private void turnDown(){this.bitmapObject = this.bitmapObjectD;}
    private void recover(){this.bitmapObject = this.bitmapObjectR;}
    void hitPointLoss(){this.hitPoint-=0.5;}
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    boolean Dead(){return isDead;}
    void move(){
        if(location.x<objectWidth*25){
            location.x+=speed;
        }else{
            this.turnDown();
            location.y+=speed;
        }
        if(location.y<screenHeight/2-6*objectHeight){
            this.recover();
            location.y=screenHeight/2-6*objectHeight;
            location.x+=speed;
        }

    }
    void dropDead(){
        if(this.hitPoint==0){
            isDead = true;
            this.bitmapObject=bitmapObjectDead;
        }
    }
    void pause(){
        speed=0;
    }
    void resume(){
        speed=1;
    }
    BitmapDrawable beTransparent(BitmapDrawable drawable){
        drawable.setAlpha(100);
        return drawable;
    }
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmapObject, location.x, location.y, paint);
    }
    Bitmap getBitmapObject(){return this.bitmapObject;}
    int getLocationX(){return location.x;}
    int getLocationY(){return location.y;}
    int getScreenWidth(){return screenWidth;}
    int getScreenHeight(){return screenHeight;}
    int getObjectWidth(){return objectWidth;}
    int getObjectHeight(){return objectHeight;}
    int getSquareSize(){return CONSTANT.SQUARE_SIZE;}

}
