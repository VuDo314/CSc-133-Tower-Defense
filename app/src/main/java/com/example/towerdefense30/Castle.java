package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

class Castle extends GameObject{
    private Point location;
    private Bitmap bitmapObject;
    //private Bitmap bitmapObjectR;
    private int squareConst; // the length of each small square side

    private int objectWidth;
    private int objectHeight;

    //Constructor
    Castle(Context context, Point size) {
        super(context, size);
        this.squareConst = CONSTANT.SQUARE_SIZE;
        objectWidth = squareConst * 4;
        objectHeight = squareConst * 4;
        location = new Point(squareConst * 23, squareConst * 25);
        bitmapObject = this.setBitmapObject(context, R.drawable.castle);
        bitmapObject = this.rotateBitmap(bitmapObject, CONSTANT.LEFT);
    }

    //set bitmap Object
    private Bitmap setBitmapObject(Context context, int id){
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmapObject = Bitmap.createScaledBitmap(this.bitmapObject, objectWidth, objectHeight, false);
        return bitmapObject;
    }

    //draw the image onto Canvas
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmapObject, location.x, location.y, paint);
    }
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    private Bitmap rotateBitmap(Bitmap bitmapObject, int degree){
        Matrix matrix = new Matrix();
        matrix.preScale(1, 1);
        matrix.preRotate(degree);
        this.bitmapObject = Bitmap.createBitmap(bitmapObject,0,0, objectWidth, objectHeight, matrix, true);
        return this.bitmapObject;
    }

    int getLocationX(){
        return location.x;
    }

    int getLocationY(){
        return location.y;
    }

    int countIntruders(ArrayList<Enemy> enemies){
        int counter=0;
        for(Enemy enemy: enemies){
            if(enemy.getLocationX()>=(this.getLocationX()+ squareConst)&&enemy.getLocationY()>this.getLocationY()){
                counter++;
            }
        }
        return counter;
    }
}
