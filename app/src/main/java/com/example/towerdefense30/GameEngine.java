package com.example.towerdefense30;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

@SuppressLint("ViewConstructor")
public class GameEngine extends SurfaceView implements Runnable, GameEngineBroadcaster{
    private Thread thread = null;
    Boolean running = true;
    private Map map;
    private Tower tower;
    private ArrayList<Enemy> enemies;
    private Renderer renderer;
    private GameState gameState;
    private Castle castle;
    private HUD hud;
    int r1;
    int r2;
    private ArrayList<InputObserver> inputObservers = new ArrayList();

    public GameEngine(Context context, Point size) {
        super(context);
        UIController uiController = new UIController(this);
        enemies = new ArrayList<>();
        map = new Map(context, size);
        tower = new Tower(context, size);
         new Projectile(context, size, tower);
         for(int i=0; i < CONSTANT.WAVE1_ENEMY;i++){
             Enemy g = new Enemy(context, size);
             g.setBitmapObject(context);
             enemies.add(g);
         }
        for(int i = 0; i< enemies.size(); i++) {
            enemies.get(i).setLocation(i* enemies.get(i).getSquareSize()*2, enemies.get(i).getSquareSize()*12);
        }
        Random rand = new Random();
        r1 = rand.nextInt(enemies.size());
        r2=r1;
        while(r1==r2){
            r2 = rand.nextInt(enemies.size());
        }
        renderer = new Renderer(this);
        gameState = new GameState();
        castle = new Castle(context, size);
        hud = new HUD(context, size);
    }
    public void addObserver(InputObserver o){
        inputObservers.add(o);
    }
    @Override
    public void run() {
        while(gameState.getThreadRunning()){
                renderer.draw(castle, map, tower, enemies, r1, r2, gameState, hud);
        }
    }
    void startThread(){
        gameState.startThread();
        thread = new Thread(this);
        thread.start();
    }
    void stopThread(){
        gameState.stopEverything();
        try{
            thread.join();
        }catch(InterruptedException e){
            Log.e("Exception", "stopThread()"+e.getMessage());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        for(InputObserver o : inputObservers){
            o.handleInput(motionEvent, gameState, hud.getControlsR());
        }
        return true;
    }
}
