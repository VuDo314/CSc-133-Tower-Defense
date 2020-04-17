package com.example.towerdefense30;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.logging.Level;
//Renderer is the class that manages to render all the object drawings
class Renderer {
    private int S;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private int screenHeight;
    private int screenWidth;
    Renderer(SurfaceView sv){
        paint = new Paint();
        surfaceHolder = sv.getHolder();
        S = CONSTANT.SQUARE_SIZE;
    }
    void draw(Castle castle, Map map, Tower tower, ArrayList<Enemy>enemies, GameState gs, HUD hud)  {
        if(surfaceHolder.getSurface().isValid()){
            this.canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,0,0,0));
            gs.startDrawing(); //so far make the startDrawing() not private to test whether GameState class work or not
            if(gs.getDrawing()) {
                map.draw(this.canvas, this.paint);
                castle.draw(this.canvas, this.paint);
               if(gs.getConstruct()){
                       tower.draw(this.canvas, this.paint);
                }
                if(gs.getTime()>=gs.getTimeToSpawn()) {
                    gameObjectSpawn(enemies, gs);
                }
                tower.fire(enemies, canvas, paint, gs);
            }
            gs.increaseWarFund(tower.countEnemyLoss(enemies));
            gs.loseLife(castle.countIntruders(enemies));
            hud.draw(canvas, paint, gs);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    private void gameObjectSpawn(ArrayList<Enemy> enemies, GameState gameState) {
            int i=enemies.size()-1;
            enemies.get(i).draw(canvas, paint);
             if(gameState.getPaused()){
                enemies.get(i).pause();
            }else {
            if (enemies.get(i).Dead()) {
                enemies.get(i).pause();
            } else {
                enemies.get(i).resume();
                enemies.get(i).move();
                }
            }
            while (enemies.get(i).getLocationX() >= 2*CONSTANT.SQUARE_SIZE) {
                i=i-1;
                enemies.get(i).draw(canvas, paint);
                if(gameState.getPaused()){
                    enemies.get(i).pause();
                }else {
                    if (enemies.get(i).Dead()) {
                        enemies.get(i).pause();
                    } else {
                        enemies.get(i).resume();
                        enemies.get(i).move();
                    }
                }
                if(i<=0) break;
            }
    }
}
