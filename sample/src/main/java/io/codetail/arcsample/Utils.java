package io.codetail.arcsample;

import android.view.View;

public class Utils {
    public static float centerX(View view) {
        return view.getX() + view.getWidth() / 2;
    }

    public static float centerY(View view) {
        return view.getY() + view.getHeight() / 2;
    }
}
