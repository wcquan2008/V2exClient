package com.github.wcquan.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.github.wcquan.library.R;

/**
 * Created by WCQUAN on 2017-06-07.
 */

public class CircularLoading extends View {

    private static final int DEF_MIN_RADIUS = 5;
    private static final int DEF_CIIRCULAR_COLOR = Color.BLUE;

    private int maxRadius;
    private int minRadius;
    private int circularColor;

    private int curRadius;
    private boolean isExpanding = false;
    private CircularDrawable mDot;

    public CircularLoading(Context context) {
        this(context, null);
    }

    public CircularLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        minRadius = DEF_MIN_RADIUS;
        circularColor = DEF_CIIRCULAR_COLOR;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularLoading);
            circularColor = typedArray.getColor(R.styleable.CircularLoading_cirColor, DEF_CIIRCULAR_COLOR);
            typedArray.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int minSide = w > h ? h : w;
        maxRadius = minSide / 2;

        curRadius = maxRadius;
        initDot(new Rect(0, 0, w, h));
    }


    private void initDot(Rect rect) {
        mDot = new CircularDrawable(curRadius, circularColor);
        mDot.setBounds(rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDot.draw(canvas);
        updateDot();
        invalidate();
    }

    private void updateDot() {
        if (isExpanding) {
            curRadius++;
        } else {
            curRadius--;
        }

        if (curRadius > maxRadius) {
            curRadius = maxRadius;
            isExpanding = false;
        }

        if (curRadius < minRadius) {
            curRadius = minRadius;
            isExpanding = true;
        }
        mDot.setRadius(curRadius);
    }


    class CircularDrawable extends Drawable {
        private Paint mPaint;
        private float radius;


        public CircularDrawable(float radius, int color) {
            this.radius = radius;

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(color);
        }


        @Override
        public void draw(Canvas canvas) {
            Rect bounds = getBounds();
            canvas.drawCircle(bounds.centerX(), bounds.centerY(), radius, mPaint);
        }

        @Override
        public void setAlpha(int alpha) {
            if (alpha != mPaint.getAlpha()) {
                mPaint.setAlpha(alpha);
                invalidateSelf();
            }
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            mPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

        public int getColor() {
            return mPaint.getColor();
        }

        public void setColor(int color) {
            mPaint.setColor(color);
            invalidateSelf();

        }

        public float getRadius() {
            return radius;
        }

        public void setRadius(float radius) {
            this.radius = radius;
            invalidateSelf();
        }
    }
}