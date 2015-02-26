package io.codetail.arcsample;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class Utils {
    public static float centerX(View view){
        return ViewHelper.getX(view) + view.getWidth()/2;
    }

    public static float centerY(View view){
        return ViewHelper.getY(view) + view.getHeight()/2;
    }
}
