package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

class Tower extends GameObject{
    private Point location;
    private Bitmap bitmapObject;
    private int S; // Small square size
    //private final int SQUARE_SIZE = 50; //create constant size of each square 50 pixels
    private boolean withinRange = false;
    private  int range;
    private Projectile projectile;

    Tower(Context context, Point size) {
        super(context, size);
        this.S = CONSTANT.SQUARE_SIZE;
        Map map = new Map(context, size);
        this.bitmapObject = this.setBitmapObject(context, R.drawable.tower);
        this.location = new Point(S * 22, S * 14);
        projectile=new Projectile(context, size,this);
        range = 6*S;
    }
    private Bitmap setBitmapObject(Context context, int id){
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmapObject = Bitmap.createScaledBitmap(this.bitmapObject, 2 * S, 2 * S, false);
        return bitmapObject;
    }

    private void InRange(){

        withinRange = true;
    }

    private boolean RangeFlag(){
        return withinRange;
    }

    //distance from enemy to the tower
    private int distance(Enemy enemy){
        return (int) Math.sqrt(Math.pow(Math.abs(enemy.getLocationX()-this.getLocationX()), 2)+Math.pow(Math.abs(enemy.getLocationY()-this.getLocationY()),2));
    }

    //draw the tower
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmapObject, location.x, location.y, paint);
    }

    void fire(ArrayList<Enemy> enemies, Canvas canvas, Paint paint, int r1, int r2, GameState gameState){
            for(Enemy g: enemies) {
                if (gameState.getPaused()) {
                    projectile.pause();
                } else {
                    if (this.distance(g) < this.getRange() && !g.Dead()) {
                        this.InRange();
                        if (this.RangeFlag()) {
                            projectile.draw(canvas, paint);
                            projectile.move(g, this);
                            if (projectile.getLocationX() < 0 || projectile.getLocationY() < 0) {
                                projectile.setLocation(projectile.getLocationX(), projectile.getLocationY());
                                projectile.draw(canvas, paint);
                                projectile.move(g, this);
                            }
                            enemies.get(r1).hitPointLoss();
                            enemies.get(r1).dropDead();
                            enemies.get(r2).hitPointLoss();
                            enemies.get(r2).dropDead();
                        }
                    } else {
                        this.withinRange = false;
                    }
                }
            }
    }
    void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    int countEnemyLoss(ArrayList<Enemy>enemies){
        int counter=0;
        for(Enemy enemy: enemies){
            if(enemy.Dead()){
                counter++;
            }
        }
        return counter;
    }
    int getLocationX(){return location.x;}
    int getLocationY(){return location.y;}
    private int getRange(){return range;}

}
