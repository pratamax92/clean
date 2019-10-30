package com.cartenz.component_design;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;

public class ColorHelper {
    public static String getThemeColorInHex(@NonNull Context context, @NonNull String colorName, @AttrRes int attribute) {
        TypedValue outValue = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getTheme().resolveAttribute(attribute, outValue, true);
        } else {
            // get color defined for AppCompat
            int appCompatAttribute = context.getResources().getIdentifier(colorName, "attr", context.getPackageName());
            context.getTheme().resolveAttribute(appCompatAttribute, outValue, true);
        }
        return String.format("#%06X", (0xFFFFFF & outValue.data));
    }

    public static String convertIntColorToHex(int intColor){
        return String.format("#%06X", (0xFFFFFF & intColor));
    }

    public static int manipulateColor(int color, float factor) { //You will want to use a factor less than 1.0f to darken. try 0.8f.
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }
}
