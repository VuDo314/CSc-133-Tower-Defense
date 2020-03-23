package com.example.towerdefense30;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
public class UIController implements InputObserver {
    private Path path = new Path();
    public UIController(GameEngineBroadcaster gb){
        gb.addObserver(this);
    }
    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons, Renderer r) {
        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() & MotionEvent.ACTION_MASK;
        if(eventType==MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP){
            if(buttons.get(HUD.PAUSE).contains(x,y)){
                if(!gameState.getPaused()){
                    gameState.pause();
                }else if(gameState.getGameOver()){
                    gameState.startNewGame();
                }else if(gameState.getPaused()&&!gameState.getGameOver()){
                    gameState.resume();
                }
            }else if(buttons.get(HUD.CONSTRUCT).contains(x,y)){
                gameState.freeze(); //in order to construct the tower, everything has to be frozen;

            }
        }
    }
    public Point touchEvent(MotionEvent event){
        int x =(int) event.getX();
        int y =(int) event.getY();
        Point p = new Point(x,y);
        return p;
    }

}
