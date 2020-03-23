package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceView;


public class OnTouchEvent extends SurfaceView {
    private Path path = new Path();
    public OnTouchEvent(Context context) {
        super(context);

    }
    /*@Override
    public Boolean OnTouchEvent(MotionEvent event){
        float xPos = event.getX();
        float yPos = event.getY();
        return true;
    }*/
}
