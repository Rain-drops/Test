package com.sgj.quicksidebar.tipsview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.sgj.quicksidebar.R;

/**
 * Created by John on 2016/4/14.
 */
public class QuickSideBarTipsItemView extends View {


    public QuickSideBarTipsItemView(Context context) {
        super(context);
    }

    public QuickSideBarTipsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuickSideBarTipsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
