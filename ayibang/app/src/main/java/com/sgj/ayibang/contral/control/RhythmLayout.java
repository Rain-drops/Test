package com.sgj.ayibang.contral.control;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by John on 2016/4/20.
 */
public class RhythmLayout extends HorizontalScrollView{

    private IRhythmItemListener mListener;
    private Context mContext;

    private RhythmAdapter mAdapter;
    private Handler mHandler;

    public void setmListener(IRhythmItemListener mListener) {
        this.mListener = mListener;
    }

    public RhythmLayout(Context context) {
        this(context, null);
    }

    public RhythmLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RhythmLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();

    }

    private void init() {

    }
}
