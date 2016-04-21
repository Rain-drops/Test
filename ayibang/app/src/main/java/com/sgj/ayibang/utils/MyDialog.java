package com.sgj.ayibang.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sgj.ayibang.R;

/**
 * Created by John on 2016/4/20.
 */
public class MyDialog {
    LayoutInflater mLayoutInflater;

    public Dialog createLoadingDialog(Context context, String msg){

        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.loading_dialog, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dialog_view);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_loading);
        TextView textView = (TextView) view.findViewById(R.id.tv_loading);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.load_animation);

        imageView.setAnimation(animation);
        textView.setText(msg);

        Dialog dialog = new Dialog(context, R.style.loading_dialog);

        dialog.setCancelable(true); // 不可以用返回键取消

        dialog.setContentView(linearLayout, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        return dialog;
    }
}
