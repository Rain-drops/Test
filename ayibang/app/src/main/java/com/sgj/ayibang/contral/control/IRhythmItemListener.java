package com.sgj.ayibang.contral.control;

/**
 * Created by John on 2016/4/20.
 */
public abstract interface IRhythmItemListener {
    public abstract void onRhythmItemChanged(int paramInt);

    public abstract void onSelected(int paramInt);

    public abstract void onStartSwipe();
}
