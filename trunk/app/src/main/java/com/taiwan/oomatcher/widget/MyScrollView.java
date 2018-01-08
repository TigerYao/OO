package com.taiwan.oomatcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

    private float yTouch;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                yTouch = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curY = ev.getY();
                this.scrollTo(0, (int) (getScrollY() - curY + yTouch));//定位
                yTouch = curY;
                return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}