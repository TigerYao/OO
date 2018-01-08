package com.taiwan.oomatcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by TigerYao on 2018/1/7.
 */

public class GridDirectView extends GridView {
    public GridDirectView(Context context) {
        super(context);
    }

    public GridDirectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridDirectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
