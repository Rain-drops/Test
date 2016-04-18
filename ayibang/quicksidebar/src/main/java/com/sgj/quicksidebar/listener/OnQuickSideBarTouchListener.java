package com.sgj.quicksidebar.listener;

/**
 * Created by John on 2016/4/14.
 */
public interface OnQuickSideBarTouchListener {
    void onLetterChanged(String letter,int position,int itemHeight);
    void onLetterTouching(boolean touching);
}
