package com.ovwvwvo.common.mvp.presenter;

/**
 * Created by mac on 16/8/16.
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
