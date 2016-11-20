package com.ovwvwvo.common.mvp;

/**
 * Created by mac on 16/8/16.
 */
public interface MvpView<T> {

    void onDataLoaded(T model);

    void onShowError(String msg);

    void showLoading();

    void hideLoading();
}