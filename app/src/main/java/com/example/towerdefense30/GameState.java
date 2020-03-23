package com.example.towerdefense30;

import android.content.Context;
import android.content.SharedPreferences;

final class GameState {
    private static volatile boolean threadRunning = false;
    private static volatile boolean paused = true;
    private static volatile boolean gameOver = true;
    private static volatile boolean drawing = false;
    private static volatile boolean frozen = true;

    private GameStarter gameStarter;
    private int score;
    private int highscore;
    private int hitPoint;
    private int resource;
    private SharedPreferences.Editor editor;

    GameState(GameStarter gameStarter, Context context){
        this.gameStarter = gameStarter;
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("highscore", Context.MODE_PRIVATE);
        editor = prefs.edit();
        highscore = prefs.getInt("hi_score",0);
    }
    private void endGame(){
        gameOver = true;
        paused = true;
        if(score>highscore){
            highscore = score;
            editor.putInt("hi_score", highscore);
            editor.commit();
        }
    }
    void startNewGame(){
        score = 0;
        hitPoint = 100;
        resource = 1000;
        stopDrawing();
        gameStarter.deSpawnRespawn();
        resume();

        startDrawing();
    }
    void loseLife(/*sound maybe*/){
        hitPoint--;
        if(hitPoint==0){
            pause();
            endGame();
        }
    }

    int getResource(){
        return resource;
    }

    void setResource(int resource){
        this.resource = resource;
    }

    int getHitPoint(){
        return hitPoint;
    }
    void increaseScore(){
        score++;
    }
    int getScore(){
        return score;
    }
    int getHighscore(){
        return highscore;
    }
    void pause(){
        paused = true;
    }
    void resume(){
        gameOver = false;
        paused = false;
    }
    void stopEverything(){
        paused = true;
        gameOver = true;
        threadRunning = false;
    }
    void freeze(){
        frozen = true;
    } //the state for constructing towers

    boolean getThreadRunning(){
        return threadRunning;
    }
    void startThread(){
        threadRunning = true;
    }
    private void stopDrawing(){
        drawing = false;
    }
    private void startDrawing(){
        drawing = true;
    }
    boolean getDrawing(){
        return drawing;
    }
    boolean getPaused(){
        return paused;
    }
    boolean getGameOver(){
        return gameOver;
    }
    boolean getFrozen(){
        return frozen;
    }
}
