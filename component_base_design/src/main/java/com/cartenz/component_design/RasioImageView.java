package com.cartenz.component_design;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class RasioImageView extends androidx.appcompat.widget.AppCompatImageView {
    private int GET_WIDTH_BY_HEIGHT = 0;
    private int GET_HEIGHT_BY_WIDTH = 1;
    private int DEFAULT = 0;

    private float numerator;
    private float divider;
    private int compare;

    public RasioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public RasioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, DEFAULT);
    }

    public RasioImageView(Context context) {
        super(context);
        init(context, null, DEFAULT);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RasioLinearLayout, defStyle, 0);
        compare = typedArray.getInt(R.styleable.RasioLinearLayout_compare, DEFAULT);
        numerator = typedArray.getFloat(R.styleable.RasioLinearLayout_numerator, DEFAULT); //pembilang
        divider = typedArray.getFloat(R.styleable.RasioLinearLayout_divide, DEFAULT); //pembagi


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (divider > 0) {
            if (compare == GET_WIDTH_BY_HEIGHT) {
                width = (int) (height * numerator / divider);
            } else if (compare == GET_HEIGHT_BY_WIDTH) {
                height = (int) (width * numerator / divider);
            }
        }
        setMeasuredDimension(width, height);
    }


}