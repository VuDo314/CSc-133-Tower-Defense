package com.example.towerdefense30;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public interface InputObserver {
    void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> controls, Renderer r);
    Point touchEvent(MotionEvent event);
}
