package com.example.superkomponenty;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomCircleView extends View {
    private Paint paint;
    private float radius = 100f;
    private float x = 250f;
    private float y = 250f;
    private int circleColor = Color.RED;

    public CustomCircleView(Context context) {
        super(context);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(circleColor);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, radius, paint);
    }

    // Metoda do zmiany koloru
    public void changeCircleColor(int color) {
        this.circleColor = color;
        paint.setColor(circleColor);
        invalidate(); // odświeżenie widoku
    }

    // Metoda do zmiany rozmiaru
    public void changeCircleSize(float newRadius) {
        this.radius = newRadius;
        invalidate();
    }

    // Obsługa dotknięcia ekranu - zmiana koloru na losowy oraz animacja rozmiaru
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int randomColor = Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
            changeCircleColor(randomColor);

            // Inicjacja animacji koła - zmiana pozycji i rozmiaru
            animateCircle(event.getX(), event.getY());
            animateCircleSize();

            return true;
        }
        return false;
    }

    // Animacja pozycji koła
    public void animateCircle(float newX, float newY) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(this, "x", newX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(this, "y", newY);
        animatorX.setDuration(1000);
        animatorY.setDuration(1000);
        animatorX.start();
        animatorY.start();
    }

    // Animacja zmiany rozmiaru koła
    public void animateCircleSize() {
        // Losowy nowy rozmiar (między 50f a 200f)
        float newRadius = 50f + (float)(Math.random() * 150f);
        ObjectAnimator animatorSize = ObjectAnimator.ofFloat(this, "radius", this.radius, newRadius);
        animatorSize.setDuration(1000);
        animatorSize.start();
    }

    // Getter i setter dla radius, wymagane dla ObjectAnimator
    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate(); // Odświeżenie widoku po zmianie rozmiaru
    }
}
