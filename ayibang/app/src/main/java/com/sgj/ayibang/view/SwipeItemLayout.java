package com.sgj.ayibang.view;

import android.content.Context;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.sgj.ayibang.adapter.SwipeListAdapter;

/**
 * Created by John on 2016/3/31.
 */
public class SwipeItemLayout extends FrameLayout {

    private View contentView = null;
    private View menuView = null;
    //用来修饰动画效果，定义动画的变化率
    private Interpolator openInterpolator = null;
    private Interpolator closeInterpolator = null;
    //
    private ScrollerCompat mOpenScroller;
    private ScrollerCompat mCloseScroller;

    private int mBaseX;
    private int mDownX;
    private int state = STATE_CLOSE;

    private static final int STATE_CLOSE = 0;
    private static final int STATE_OPEN = 1;

    public SwipeItemLayout(View contentView, View menuView, Interpolator closeInterpolator, Interpolator openInterpolator) {
        super(contentView.getContext());
        this.contentView = contentView;
        this.menuView = menuView;
        this.closeInterpolator = closeInterpolator;
        this.openInterpolator = openInterpolator;

        init();

    }

    private void init() {
        setLayoutParams(new AbsListView.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        if(closeInterpolator != null){
            mCloseScroller = ScrollerCompat.create(getContext(), closeInterpolator);
        }else {
            mCloseScroller = ScrollerCompat.create(getContext());
        }
        if(openInterpolator != null){
            mOpenScroller = ScrollerCompat.create(getContext(), openInterpolator);
        }else {
            mOpenScroller = ScrollerCompat.create(getContext());
        }

        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        contentView.setLayoutParams(params);
        menuView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        addView(contentView);
        addView(menuView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        menuView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        contentView.layout(0, 0, getMeasuredWidth(), contentView.getMeasuredHeight());
        menuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() + menuView.getMeasuredWidth(),
                contentView.getMeasuredHeight());

    }

    /**
     * computeScroll：主要功能是计算拖动的位移量、更新背景、设置要显示的屏幕(setCurrentScreen(mCurrentScreen);)。
     *
     * 重写computeScroll()的原因: 调用startScroll()是不会有滚动效果的，只有在computeScroll()获取滚动情况，
     * 做出滚动的响应computeScroll在父控件执行drawChild时，会调用这个方法
     */
    @Override
    public void computeScroll() {
        if(state == STATE_OPEN){
            if(mOpenScroller.computeScrollOffset()){
                swipe(mOpenScroller.getCurrX());
                postInvalidate();
            }
        }else {
            if(mCloseScroller.computeScrollOffset()){
                swipe(mBaseX - mCloseScroller.getCurrX());
                postInvalidate();
            }
        }

    }

    public void openMenu(){
        if(state == STATE_CLOSE){
            state = STATE_OPEN;
            swipe(menuView.getWidth());
        }
    }

    public void closeMenu(){
        if(state == STATE_OPEN){
            state = STATE_CLOSE;
            swipe(0);
        }
    }

    public void smoothCloseMenu(){
        state = STATE_CLOSE;
        mBaseX = -contentView.getLeft();
        mCloseScroller.startScroll(0, 0, mBaseX, 0, 350);
        postInvalidate();
    }

    public void smoothOpenMenu(){
        state = STATE_OPEN;
        //startX 水平方向滚动的偏移值，以像素为单位。正值表明滚动将向左滚动
        // startY 垂直方向滚动的偏移值，以像素为单位。正值表明滚动将向上滚动
        // dx 水平方向滑动的距离，正值会使滚动向左滚动
        // dy 垂直方向滑动的距离，正值会使滚动向上滚动
        // duration 执行时间，缺省值为 250ms
        mOpenScroller.startScroll(-contentView.getLeft(), 0, menuView.getWidth(), 0, 350);
        postInvalidate();
    }

    private void swipe(int width) {
        if(width > menuView.getWidth()){
            width = menuView.getWidth();
        }
        if(width < 0){
            width = 0;
        }
        // 子控件相对与父控件的距离(left, top, right, bottom)
        contentView.layout(-width, contentView.getTop(), contentView.getWidth() - width,
                getMeasuredHeight());

        menuView.layout(contentView.getWidth() - width, menuView.getTop(),
                contentView.getWidth() + menuView.getWidth() - width, menuView.getBottom());

    }

    public boolean onSwipe(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                // Log.i("byz", "downX = " + mDownX + ", moveX = " + event.getX());
                int dis = (int) (mDownX - event.getX());
                if (state == STATE_OPEN) {
                    dis += menuView.getWidth();
                }
                swipe(dis);
                break;
            case MotionEvent.ACTION_UP:
                if ((mDownX - event.getX()) > (menuView.getWidth() / 2)) {
                    // open
                    smoothOpenMenu();
                } else {
                    // close
                    smoothCloseMenu();
                    return false;
                }
                break;
        }
        return true;
    }

    public boolean isOpen() {
        return state == STATE_OPEN;
    }

    public View getContentView() {
        return contentView;
    }

    public View getMenuView() {
        return menuView;
    }

}
