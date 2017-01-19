package com.garyhu.statusmodel.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.garyhu.statusmodel.R;
import com.garyhu.statusmodel.material.MaterialBackgroundDetector;


/**
 * Created by Administrator on 2016/8/15.
 * 自定义的带有点击效果的TextView
 */
public class MaterialTextView extends TextView implements MaterialBackgroundDetector.Callback{
    private MaterialBackgroundDetector mDetector;

    public MaterialTextView(Context context) {
        super(context);
        init(null,0);
    }

    public MaterialTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs,0);
    }

    public MaterialTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MaterialTextView, defStyle, 0);
        int color = a.getColor(R.styleable.MaterialTextView_maskColor, MaterialBackgroundDetector.DEFAULT_COLOR);
        a.recycle();
        mDetector = new MaterialBackgroundDetector(getContext(), this, this, color);
    }

    @Override
    public boolean performClick() {
        return mDetector.handlePerformClick();
    }

    @Override
    public boolean performLongClick() {
        return mDetector.handlePerformLongClick();
    }

    @Override
    public void performClickAfterAnimation() {
        super.performClick();
    }

    @Override
    public void performLongClickAfterAnimation() {
        super.performLongClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDetector.onSizeChanged(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean superResult = super.onTouchEvent(event);
        return mDetector.onTouchEvent(event, superResult);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            return;
        }
        mDetector.draw(canvas);
    }
}
