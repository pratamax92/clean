package com.cartenz.component_design.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.cartenz.component_design.R;


public class ImageViewRounded extends androidx.appcompat.widget.AppCompatImageView {

    private static final int DEFAULT_RADIUS = 24;
    private int radius = DEFAULT_RADIUS;

    private static final int PADDING = 8;
    private Paint mBorderPaint= new Paint();

    public ImageViewRounded(Context context) {
        super(context);
    }

    public ImageViewRounded(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ImageViewRounded(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageViewRounded, defStyle, 0);
        radius = a.getDimensionPixelSize(R.styleable.ImageViewRounded_ivr_radius, DEFAULT_RADIUS);

        init();
    }

    private void init() {
        clipPath.addRoundRect(0, 0, this.getWidth(), this.getHeight(), radius, radius, Path.Direction.CW);
//        mBorderPaint.setAntiAlias(true);
//        mBorderPaint.setStyle(Paint.Style.STROKE);
//        mBorderPaint.setColor(Color.GREEN);
//        mBorderPaint.setStrokeWidth(radius);
//
        invalidate();
    }

    private void initBorder() {
//        mBorderPaint.setAntiAlias(true);
//        mBorderPaint.setStyle(Paint.Style.STROKE);
//        mBorderPaint.setColor(Color.GREEN);
//        mBorderPaint.setStrokeWidth(radius);
    }

    Path clipPath = new Path();
    @Override
    protected void onDraw(Canvas canvas) {
//        initBorder();

        //RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());//left top rigth buttom
        //clipPath.addRoundRect(0, 0, this.getWidth(), this.getHeight(), radius, radius, Path.Direction.CW);
        clipPath.addRoundRect(0, 0, this.getWidth(), this.getHeight(), radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        //canvas.drawRect(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING, mBorderPaint);

        super.onDraw(canvas);
    }
}