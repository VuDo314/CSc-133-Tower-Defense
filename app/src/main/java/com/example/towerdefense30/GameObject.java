package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class GameObject {
    private Bitmap bitmapObject;
    GameObject(Context context){

    }

    Bitmap setBitmapObject(Context context, int objectWidth, int objectHeight, int id) {
        this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
        this.bitmapObject= Bitmap.createScaledBitmap(this.bitmapObject, objectWidth, objectHeight, false);
        return bitmapObject;
    }
}
