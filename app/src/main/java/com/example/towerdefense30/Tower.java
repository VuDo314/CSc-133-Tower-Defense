package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

 class Tower extends GameObject {
    private Point location;
    private Bitmap bitmapObject;
    private int S;
    Tower(Context context) {
        super(context);
        this.S = CONSTANT.SQUARE_SIZE;
        this.bitmapObject = this.setBitmapObject(context,2*S, 2*S, R.drawable.tower);
        this.location = new Point();
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmapObject, location.x, location.y, paint);
    }
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    int getLocationX(){return location.x;}
    int getLocationY(){return location.y;}
}
