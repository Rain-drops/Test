package com.sgj.quicksidebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sgj.quicksidebar.tipsview.QuickSideBarTipsItemView;

/**
 * Created by John on 2016/4/14.
 */
public class QuickSideBarTipsView extends RelativeLayout {
    private QuickSideBarTipsItemView mTipsView;

    public QuickSideBarTipsView(Context context) {
        this(context, null);
    }

    public QuickSideBarTipsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickSideBarTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }



}