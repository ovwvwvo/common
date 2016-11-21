package com.ovwvwvo.common.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ovwvwvo.common.mvp.presenter.Presenter;
import com.ovwvwvo.jlibrary.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mac on 16/8/16
 */
public abstract class BaseFragment<P extends Presenter> extends Fragment {

    P presenter;
    Context mContext;

    private CompositeSubscription mCompositeSubscription;

    abstract P createPresenter();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();

        presenter = createPresenter();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(mContext);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(mContext);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    protected void toastShow(String message) {
        ToastUtil.showShort(mContext, message);
    }

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null)
            mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(subscription);
    }
}
