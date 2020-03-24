package com.example.towerdefense30;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Renderer {
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Point spawn1; //spawning points
    private Point tower; //Initial tower location
    //private Point spawn2; //spawning points
    Renderer(SurfaceView sh){
        surfaceHolder = sh.getHolder();
        paint = new Paint();
        spawn1 = new Point(0, 10);
        tower = new Point(22, 12);
       //spawn2 = new Point(0, 13);
    }
    void draw(GameState gameState, HUD hud, Tower t, Map m, Enemy e) {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 0, 0, 0));
            if (gameState.getDrawing()) {
                // Draw all the game objects here
                m.draw(canvas, paint,gameState);
                t.draw(canvas, paint, gameState, tower);

                e.draw(canvas, paint, gameState, spawn1);
               // e.draw(canvas, paint, gameState, spawn2);
                e.move();
                e.draw(canvas, paint, gameState, spawn1);
            }
            if(gameState.getGameOver()) {
                // Draw a background graphic here
            }
            hud.draw(canvas, paint, gameState);
            // Draw a particle system explosion here
            // Now we draw the HUD on top of everything else
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
