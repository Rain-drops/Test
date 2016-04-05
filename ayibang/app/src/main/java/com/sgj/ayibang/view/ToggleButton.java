package com.sgj.ayibang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by John on 2016/4/5.
 */
public class ToggleButton extends View {

//    private SpringSystem springSystem;
//    private Spring spring ;
    /** */
    private float radius;
    /** 开启颜色*/
    private int onColor = Color.parseColor("#4ebb7f");
    /** 关闭颜色*/
    private int offBorderColor = Color.parseColor("#dadbda");
    /** 灰色带颜色*/
    private int offColor = Color.parseColor("#ffffff");
    /** 手柄颜色*/
    private int spotColor = Color.parseColor("#ffffff");
    /** 边框颜色*/
    private int borderColor = offBorderColor;
    /** 画笔*/
    private Paint paint ;
    /** 开关状态*/
    private boolean toggleOn = false;
    /** 边框大小*/
    private int borderWidth = 2;
    /** 垂直中心*/
    private float centerY;
    /** 按钮的开始和结束位置*/
    private float startX, endX;
    /** 手柄X位置的最小和最大值*/
    private float spotMinX, spotMaxX;
    /**手柄大小 */
    private int spotSize ;
    /** 手柄X位置*/
    private float spotX;
    /** 关闭时内部灰色带高度*/
    private float offLineWidth;
    /** */
    private RectF rect = new RectF();
    /** 默认使用动画*/
    private boolean defaultAnimate = true;

    /** 是否默认处于打开状态*/
    private boolean isDefaultOn = false;

    private OnToggleChanged listener;

    public interface OnToggleChanged{
        /**
         * @param on
         */
        public void onToggle(boolean on);
    }

    public ToggleButton(Context context) {
        super(context);
    }

    public ToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
