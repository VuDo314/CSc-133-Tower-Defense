package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

 public class GameObject {
   private Point location;
   private Bitmap bitmapObject;
   private Point size;
   private int objectWidth;
   private int objectHeight;
   GameObject(Point size){
        this.objectWidth = size.x/10;
        this.objectHeight = size.y/10;
   }
   Bitmap setBitmapObject(Context context, int id){
       this.bitmapObject = BitmapFactory.decodeResource(context.getResources(), id);
       this.bitmapObject = Bitmap.createScaledBitmap(this.bitmapObject, objectWidth, objectHeight, false);
       return bitmapObject;
   }
   void setLocation(int x, int y){
       location.x = x;
       location.y = y;
   }
}
