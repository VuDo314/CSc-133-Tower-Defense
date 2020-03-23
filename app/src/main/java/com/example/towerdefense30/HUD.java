package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class HUD extends GameObject {
    private int textFormatting;
    private Point location = new Point();
    //private Point buttonSize;
    private ArrayList<Bitmap> controls;
    private ArrayList<Rect> controlsR;
    private int screenHeight;
    private int screenWidth;
    private final int numButtons = 4;
    private ArrayList<Integer>label;
    static int PAUSE = 1;
    static int CONSTRUCT = 2;
    private static int RECYCLE = 3;

    private final int SQUARE_SIZE = 50; //create constant size of each square 50 pixels

    HUD(Context context, Point size) {
        super(size);
        screenHeight = size.y;
        screenWidth = size.x;
        textFormatting = SQUARE_SIZE;
        //buttonSize = new Point();
        //buttonSize.x = SQUARE_SIZE * 2;
        //buttonSize.y = SQUARE_SIZE * 2;
        label = new ArrayList<>();
        label.add(0,R.drawable.play);
        label.add(1,R.drawable.pause);
        label.add(2,R.drawable.construct);
        label.add(3,R.drawable.recycle);

        //set list of bitmap objs
        controls = new ArrayList<>();
        for(int i=0; i<numButtons;i++) {
            controls.add(i, this.setBitmapObject(context, label.get(i)));
        }
        createControlsR();
    }
    private void createControlsR(){

        //set the touch area of each button
        Rect playR = new Rect( 0, 0, SQUARE_SIZE * 2, SQUARE_SIZE * 2);
        Rect pauseR = new Rect( 0, 0, SQUARE_SIZE * 2, SQUARE_SIZE * 2);
        Rect constructR = new Rect(SQUARE_SIZE * 3, 0, SQUARE_SIZE * 5, SQUARE_SIZE * 2);
        Rect recycleR = new Rect (SQUARE_SIZE * 6, 0, SQUARE_SIZE * 8, SQUARE_SIZE * 2);

        controlsR = new ArrayList<>();
        int PLAY = 0;
        controlsR.add(PLAY, playR);
        controlsR.add(PAUSE, pauseR);
        controlsR.add(CONSTRUCT, constructR);
        controlsR.add(RECYCLE, recycleR);

    }
    void setLocation(){
        location.x = 0; location.y=0;
    }

    Point getLocation(){
        return location;
    }

    void draw(Canvas canvas, Paint paint, GameState gameState){
        paint.setColor(Color.argb(255,125, 125, 255));
        paint.setTextSize(textFormatting);

        drawLives(canvas, paint, gameState);
        drawResource(canvas, paint, gameState);
        drawHiScore(canvas, paint, gameState);
        drawScore(canvas, paint, gameState);


        if(gameState.getGameOver()){
            //canvas.drawBitmap(this.controls.get(4), location.x*buttonSize.x , location.y*buttonSize.y, paint);
            paint.setTextSize(textFormatting * 3);
            canvas.drawText("PRESS START", screenWidth/4, screenHeight/2, paint);
        }
        if(gameState.getPaused()&&!gameState.getGameOver()){
            paint.setTextSize(SQUARE_SIZE * 5);
            canvas.drawText("PAUSED", SQUARE_SIZE * 8, SQUARE_SIZE * 12, paint);
        }
        //state for constructing towers
        if(gameState.getFrozen()&&!gameState.getPaused()&&!gameState.getGameOver()){
            //paint.setTextSize(textFormatting*5);
            //canvas.drawText("FROZEN", screenWidth/3, screenHeight/2, paint);

        }


        drawControls(canvas, paint);


        setLocation();
        for(int i = 0; i<numButtons;i++) {
            //if the game is not pause (or running), skip play button and display pause instead
            if(!gameState.getPaused() && i == 0){
                i ++;
            }
            //if the game is pause (not running), display the play button, skipp the pause button
            if(gameState.getPaused() && i == 1){
                i ++;
            }

            canvas.drawBitmap(this.controls.get(i), location.x, 0, paint);
            location.x += 3 * SQUARE_SIZE;
        }
    }

    private void drawHiScore(Canvas canvas, Paint paint, GameState gameState){
        canvas.drawText("Hi-Score: "+gameState.getHighscore(), textFormatting * 20, textFormatting, paint);
    }
    private void drawScore(Canvas canvas, Paint paint, GameState gameState){
        canvas.drawText("Score: "+gameState.getScore(), textFormatting * 20, textFormatting * 2 - 2, paint);
    }
    private void drawLives(Canvas canvas, Paint paint, GameState gameState){
        canvas.drawText("Lives: "+gameState.getHitPoint(), textFormatting * 9, textFormatting, paint);
    }
    private void drawResource(Canvas canvas, Paint paint, GameState gameState){
        canvas.drawText("Resources: "+gameState.getResource(), textFormatting * 9, textFormatting * 2 - 2, paint);
    }

    private void drawControls(Canvas canvas, Paint paint){
        paint.setColor(Color.argb(0x00, 0xff, 0xff, 0xff));
        for(Rect r: controlsR){
            canvas.drawRect(r.left, r.top, r.right, r.bottom, paint);
        }
        paint.setColor(Color.argb(255, 255, 255, 255));
    }

    ArrayList<Rect>getControlsR(){
        return controlsR;
    }

    Bitmap setBitmapObject(Context context, int id){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        bitmap = Bitmap.createScaledBitmap(bitmap, SQUARE_SIZE * 2, SQUARE_SIZE * 2, false);
        return bitmap;
    }
}
