package com.ovwvwvo.common.view;

/**
 * Created by rawer on 17/2/28.
 */
public interface BaseView {

    void showToast(int message);

    void showProgressDialog(int message);

    void dismissProgressDialog();

    void showProgressBar();

    void dismissProgressBar();

    void showErrorMessage(Throwable throwable);

    void showNetWorkErrorMessage();
}