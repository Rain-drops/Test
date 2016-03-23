package com.sgj.listener;

/**
 * Created by John on 2016/3/22.
 */
public abstract class BastListener<T> {
    public abstract void onError(Exception e);
    public abstract void onSuccess(T result);
    public abstract void onStringResult(String result);
}
