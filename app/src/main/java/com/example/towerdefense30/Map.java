package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
// Map is a bitmap object that used to create a map of the game
class Map extends GameObject{
    private Point location;
    //private ArrayList<Point> path = new ArrayList<>();
    private Bitmap bitmapObject;
    private Bitmap bitmapPath;
    private Bitmap UIBar;
    private int horSize;
    private int verSize;
    private Bitmap castle;
    private int S; //small square size

    Map(Context context, Point size) {
        super(context, size);
        this.S = CONSTANT.SQUARE_SIZE * 4;
        this.verSize = size.y;
        this.horSize = size.x;
        //this.objectWidth = SQUARE_SIZE;
        //this.objectHeight = SQUARE_SIZE;
        this.castle = this.setBitmapObject(context, R.drawable.castle);
        S = CONSTANT.SQUARE_SIZE;
        this.UIBar = this.setBitmapObject(context, R.drawable.uibarsquare);
        this.bitmapPath = this.setBitmapObject(context, R.drawable.pathsquare);
        this.bitmapObject = this.setBitmapObject(context, R.drawable.mapsquare);
        this.location = new Point();
    }

    private Bitmap setBitmapObject(Context context, int id){
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmapObject = Bitmap.createScaledBitmap(this.bitmapObject, S, S, false);
        return bitmapObject;
    }

    //draw the map
    void draw(Canvas canvas, Paint paint){
        setLocation(0, 0);
        int w = (horSize / S) * S;
        int h = (verSize / S) * S;

        //draw the green area and UI bar
        for(int j = 0; j < verSize; ) {
            for (int i = 0; i < horSize; ) {
                if(j == 0 || j == S){
                    canvas.drawBitmap(this.UIBar, i, j, paint);
                }
                else{
                    canvas.drawBitmap(this.bitmapObject, i, j, paint);
                }
                i += S;
            }
            j += S;
        }

        //draw the brown horizontal path
        for(int i = 0; i <= S * 25;){
            canvas.drawBitmap(this.bitmapPath, i, S * 12, paint);
            canvas.drawBitmap(this.bitmapPath, i, S * 13, paint);
            i += S;
        }
        //draw the brown vertical path
        for(int j = S * 12; j <= verSize;){
            canvas.drawBitmap(this.bitmapPath,S * 24, j, paint);
            canvas.drawBitmap(this.bitmapPath,S * 25, j, paint);
            j += S;
        }

        //draw the castle
        canvas.drawBitmap(this.castle,S * 20, S * 17, paint);
    }
    private void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    int getVerSize(){return verSize;}
    int getHorSize(){return horSize;}
}
