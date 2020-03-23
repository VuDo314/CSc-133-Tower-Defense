package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class HUD extends GameObject {
    private int textFormatting;
    private Point location = new Point();
    private Point buttonSize;
    private ArrayList<Bitmap> controls;
    private ArrayList<Rect> controlsR;
    private int screenHeight;
    private int screenWidth;
    private final int numButtons = 4;
    private ArrayList<Integer>label;
    static int CONSTRUCT = 1;
    static int RECYCLE = 2;
    static int PAUSE = 3;
    static int PLAY = 0;

    HUD(Context context, Point size) {
        super(size);
        screenHeight = size.y;
        screenWidth = size.x;
        textFormatting = size.x/50;
        buttonSize = new Point();
        buttonSize.x = 100;
        buttonSize.y = 100;
        label = new ArrayList<>();
        label.add(0,R.drawable.play);
        label.add(1,R.drawable.construct);
        label.add(2,R.drawable.recycle);
        label.add(3,R.drawable.pause);
        //this.PLAY = this.setBitmapObject(context, R.drawable.play);
        controls = new ArrayList<>();
        for(int i=0; i<numButtons;i++) {
            controls.add(i, this.setBitmapObject(context, label.get(i)));
        }
        createControlsR();
    }
    private void createControlsR(){
        int buttonWidth = buttonSize.x;
        int buttonHeight = buttonSize.y;

        Rect playR = new Rect( 0, 0, buttonWidth, buttonHeight);
        Rect pauseR = new Rect( 0, 0, buttonWidth, buttonHeight);
        Rect constructR = new Rect(buttonWidth, 0, 2 * buttonWidth, buttonHeight);
        Rect recycleR = new Rect (2 * buttonWidth, 0, 3 * buttonWidth, buttonHeight);
        controlsR = new ArrayList<>();
        controlsR.add(PLAY, playR);
        controlsR.add(CONSTRUCT, constructR);
        controlsR.add(RECYCLE, recycleR);
        controlsR.add(PAUSE, pauseR);

    }
    void setLocation(){
        location.x = 0; location.y=0;
    }
    Point getLocation(){return location;}
    void draw(Canvas canvas, Paint paint, GameState gameState){
        paint.setColor(Color.argb(255,255, 255, 255));
        paint.setTextSize(textFormatting);
        canvas.drawText("Hi: "+gameState.getHighscore(), textFormatting, textFormatting, paint);
        canvas.drawText("Score: "+gameState.getScore(), textFormatting, textFormatting*2, paint);
        canvas.drawText("Lives: "+gameState.getHitPoint(), textFormatting, textFormatting*3, paint);

        if(gameState.getGameOver()){
            //canvas.drawBitmap(this.controls.get(4), location.x*buttonSize.x , location.y*buttonSize.y, paint);
            paint.setTextSize(textFormatting*5);
            canvas.drawText("PRESS START", screenWidth/4, screenHeight/2, paint);
        }
        if(gameState.getPaused()&&!gameState.getGameOver()){



            paint.setTextSize(textFormatting*5);
            canvas.drawText("PAUSED", screenWidth/3, screenHeight/2, paint);
        }
        //state for constructing towers
        if(gameState.getFrozen()&&!gameState.getPaused()&&!gameState.getGameOver()){
            //paint.setTextSize(textFormatting*5);
            //canvas.drawText("FROZEN", screenWidth/3, screenHeight/2, paint);

        }
        drawControls(canvas, paint);
        Point p = new Point(0,0);


        for(int i = 0; i<numButtons - 1;i++) {

            canvas.drawBitmap(this.controls.get(i), location.x*buttonSize.x + p.x, location.y*buttonSize.y, paint);
            p.x += 2 * buttonSize.x;
        }



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
}
