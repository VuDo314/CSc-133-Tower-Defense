package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class Map extends GameObject {
    private Point location;
    //private ArrayList<Point> path = new ArrayList<>();
    private Bitmap bitmapObject;
    private Bitmap bitmapPath;
    private int objectWidth;
    private int objectHeight;
    private int horSize;
    private int verSize;
    private final int SQUARE_SIZE = 50; //create constant size of each square 50 pixels

    Map(Context context, Point size) {
        super(size);
        this.verSize = size.y;
        this.horSize = size.x;
        this.objectWidth = SQUARE_SIZE;
        this.objectHeight = SQUARE_SIZE;
        this.bitmapPath = this.setBitmapObject(context, R.drawable.pathsquare);
        this.bitmapObject = this.setBitmapObject(context, R.drawable.mapsquare);
        this.location = new Point();
    }
    Bitmap setBitmapObject(Context context, int id){
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmapObject = Bitmap.createScaledBitmap(this.bitmapObject, objectWidth, objectHeight, false);
        return bitmapObject;
    }
    void draw(Canvas canvas, Paint paint, GameState gameState){
        setLocation(0, 0);
        int w = (horSize / objectWidth) * objectWidth;
        int h = (verSize / objectHeight) * objectHeight;

        // draw the who green area map
        for(int j = SQUARE_SIZE * 2; j < h; ) {
            for (int i = 0; i < w; ) {
               canvas.drawBitmap(this.bitmapObject, i, j, paint);
                i += SQUARE_SIZE;
            }
            j += SQUARE_SIZE;
        }

        //draw the brown horizontal path
        for(int i = 0; i <= SQUARE_SIZE * 25;){
            canvas.drawBitmap(this.bitmapPath, i, SQUARE_SIZE * 12, paint);
            canvas.drawBitmap(this.bitmapPath, i, SQUARE_SIZE * 13, paint);
            i += SQUARE_SIZE;
        }
        //draw the brown vertical path
        for(int j = SQUARE_SIZE * 2; j <= SQUARE_SIZE * 13;){
            canvas.drawBitmap(this.bitmapPath,SQUARE_SIZE * 24, j, paint);
            canvas.drawBitmap(this.bitmapPath,SQUARE_SIZE * 25, j, paint);
            j += SQUARE_SIZE;
        }


    }
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
}
