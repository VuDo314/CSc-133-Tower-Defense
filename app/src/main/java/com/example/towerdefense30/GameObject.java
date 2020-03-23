package com.example.towerdefense30;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

 public class GameObject {
   private Point location;
   private Bitmap bitmap;
   private Point size;
   private int S;

   GameObject(Point size){
       this.S = CONSTANT.SQUARE_SIZE;
   }

   Bitmap setBitmapObject(Context context, int id){
       this.bitmap = BitmapFactory.decodeResource(context.getResources(), id);
       this.bitmap = Bitmap.createScaledBitmap(this.bitmap, S, S, false);
       return bitmap;
   }
   void setLocation(int x, int y){
       location.x = x;
       location.y = y;
   }
}
