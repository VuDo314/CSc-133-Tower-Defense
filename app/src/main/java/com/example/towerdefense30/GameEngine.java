package com.example.towerdefense30;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameEngine extends SurfaceView implements Runnable, GameStarter, GameEngineBroadcaster{
    private Thread thread = new Thread(this);
    private long fps;
    private GameState gameState;
    private Renderer r;
    private HUD hud;
    private ArrayList<InputObserver> inputObservers = new ArrayList();
    UIController mUIController;
    private Tower t;
    private Map map;
    private Enemy e;
    final int MILLIS_IN_SECOND = 1000;

    GameEngine(Context context, Point size){
        super(context);
        gameState = new GameState(this, context);
        hud = new HUD(context,size);
        r = new Renderer(this);
        mUIController = new UIController(this);
        t = new Tower(context, size);
        map = new Map(context, size);
        e = new Enemy(context, size);
    }

    @Override
    public void run() {
        while(gameState.getThreadRunning()) {
            long frameStartTime = System.currentTimeMillis();

            if(!gameState.getPaused()){
                //e.move(this.getFps());
                //updates game objects;
                if(gameState.getFrozen()){

                }
            }
            //r.draw(gameState, hud, t, map);
            long timeThisFrame = System.currentTimeMillis() - frameStartTime;
            if (timeThisFrame >= 1) {
                fps = MILLIS_IN_SECOND / timeThisFrame;
            }
            r.draw(gameState, hud, t, map, e);
        }
    }
    public void stopThread(){
        //more
        gameState.stopEverything();
        try{
            thread.join();
        }catch(InterruptedException e){
            Log.e("Exception", "stopThread()"+e.getMessage());
        }
    }
    long getFps(){return this.fps;}
    public void startThread(){
        //more
        gameState.startThread();
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void deSpawnRespawn() {

    }

    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (InputObserver o : inputObservers) {
            o.handleInput(motionEvent, gameState, hud.getControlsR(),r);
        }
        return true;
    }
}
