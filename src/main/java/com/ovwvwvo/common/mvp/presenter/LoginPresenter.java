package com.ovwvwvo.common.mvp.presenter;

import com.smartstudy.jizhipigai.model.LoginRespose;
import com.smartstudy.jizhipigai.mvp.MvpView;
import com.smartstudy.jizhipigai.network.ApiCallback;
import com.smartstudy.jizhipigai.network.SubscriberCallBack;

/**
 * Created by mac on 16/8/16.
 */
public class LoginPresenter extends PresenterImp<MvpView<LoginRespose>> {

    public LoginPresenter(MvpView view) {
        attachView(view);
    }

    public void login() {
        mvpView.showLoading();
        addSubscription(apiStores.login("", "", 0),
                new SubscriberCallBack<>(new ApiCallback<LoginRespose>() {
                    @Override
                    public void onSuccess(LoginRespose model) {
                        mvpView.onDataLoaded(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.onShowError(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }

}
