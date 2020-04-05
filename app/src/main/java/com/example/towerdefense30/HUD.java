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

public class HUD extends GameObject{
    private int S;
    private Point location = new Point();
    private ArrayList<Bitmap>controls;
    private ArrayList<Rect>controlsR;
    //private final int numButtons = 4; replace with constant BUTTONS
    private ArrayList<Integer>label;
    private int screenHeight;
    private int screenWidth;
    static int PLAY = 0;
    static int PAUSE = 1;
    static int CONSTRUCT1 = 2;
    static int CONSTRUCT2 = 3;
    static int CONSTRUCT3 = 4;
    static int RECYCLE = 5;
    //static int BUILD = 6;
    private int textFormatting;

    HUD(Context context, Point size) {
        super(context, size);
        this.S = CONSTANT.SQUARE_SIZE;
        label = new ArrayList<>();
        label.add(0,R.drawable.play);
        label.add(1,R.drawable.pause);
        label.add(2,R.drawable.construct1);
        label.add(3,R.drawable.construct2);
        label.add(4,R.drawable.construct3);
        label.add(5,R.drawable.recycle);
        //label.add(6,R.drawable.buildsquare);

        //set list of bitmap objs
        controls = new ArrayList<>();
        for(int i=0; i<CONSTANT.BUTTONS;i++) {
            controls.add(i, this.setBitmapObject(context, label.get(i)));
        }
        createControlsR();
        textFormatting = size.x/50;
          screenHeight=size.y;
          screenWidth=size.x;
    }

    //clickable area for each HUD button
    private void createControlsR(){
        //set the touch area of each button
        Rect playR = new Rect( 0, 0, S * 2, S * 2);
        Rect pauseR = new Rect( 0, 0, S * 2, S * 2);
        Rect construct1 = new Rect(S * 2, 0, S * 4, S * 2);
        Rect construct2 = new Rect(S * 4, 0, S * 6, S * 2);
        Rect construct3 = new Rect(S * 6, 0, S * 8, S * 2);
        Rect recycleR = new Rect (S * 8, 0, S * 10, S * 2);


        controlsR = new ArrayList<>();
        controlsR.add(PLAY, playR);
        controlsR.add(PAUSE, pauseR);
        controlsR.add(CONSTRUCT1, construct1);
        controlsR.add(CONSTRUCT2, construct2);
        controlsR.add(CONSTRUCT3, construct3);
        controlsR.add(RECYCLE, recycleR);
    }

    ArrayList<Rect>getControlsR(){

        return controlsR;
    }

    private void drawControls(Canvas canvas, Paint paint){
        paint.setColor(Color.argb(0x00, 0xff, 0xff, 0xff));
        for(Rect r: controlsR){
            canvas.drawRect(r.left, r.top, r.right, r.bottom, paint);
        }
        paint.setColor(Color.argb(255, 255, 255, 255));
    }
    private Bitmap setBitmapObject(Context context, int id){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        bitmap = Bitmap.createScaledBitmap(bitmap, S * 2, S * 2, false);
        return bitmap;
    }
    private void setLocation(){
        location.x = 0; location.y=0;
    }

    Point getLocation(){
        return location;
    }
    void draw(Canvas canvas, Paint paint, GameState gameState){
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(S);
        drawHP(canvas, paint, gameState);
        drawWarFund(canvas, paint, gameState);
        drawTimer(canvas, paint, gameState);
        gameState.startTimer();

        if(gameState.getGameOver()){
            //canvas.drawBitmap(this.controls.get(4), location.x*buttonSize.x , location.y*buttonSize.y, paint);
            paint.setTextSize(textFormatting*5);
            canvas.drawText("PRESS PLAY" , screenWidth/4, screenHeight/2, paint);
            gameState.resetTimer();
        }
        if(gameState.getPaused()&&!gameState.getGameOver()){
            paint.setTextSize(S * 5);
            canvas.drawText("PAUSED", screenWidth/4, screenHeight/2, paint);
            gameState.pauseTimer();

        }

        if(gameState.getBuild()){


            canvas.drawBitmap(this.controls.get(6), S * 5, S * 5, paint);
            gameState.pauseTimer();

        }

        drawControls(canvas, paint);
        setLocation();
        for(int i = 0; i<CONSTANT.BUTTONS;i++) {
            //if the game is not pause (or running), skip play button and display pause instead
            if(!gameState.getPaused() && i == 0){
                i ++;
            }
            //if the game is pause (not running), display the play button, skipp the pause button
            if(gameState.getPaused() && i == 1){
                i ++;
            }
            canvas.drawBitmap(this.controls.get(i), location.x, 0, paint);
            location.x += 2 * S;
        }
    }
    private void drawHP(Canvas canvas, Paint paint, GameState gameState){
        canvas.drawText("HP: "+gameState.getHitPoint(), S * 12, S, paint);
    }
    private void drawWarFund(Canvas canvas, Paint paint, GameState gameState){
        canvas.drawText("War Fund: "+gameState.getWarFund(), S * 12, S * 2 - 2, paint);
    }
    private void drawTimer(Canvas canvas, Paint paint, GameState gameState){
        canvas.drawText("Timer: "+(int)gameState.getTime(), S * 20, S , paint);
    }
}
