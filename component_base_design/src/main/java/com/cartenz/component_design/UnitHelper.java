package com.cartenz.component_design;

import android.content.res.Resources;

public class UnitHelper {
    public static int intToDp(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToInt(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}