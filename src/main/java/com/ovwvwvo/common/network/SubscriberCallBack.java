package com.ovwvwvo.common.network;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by mac on 16/8/16.
 */
public class SubscriberCallBack<T> extends Subscriber<T> {
    private ApiCallback<T> apiCallback;

    public SubscriberCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
//            String msg = httpException.getMessage();
//            switch (code) {
//                case 504:
//                    msg = "网络不给力";
//                    break;
//            }
            apiCallback.onFailure(code, httpException.getMessage());
        } else {
            apiCallback.onFailure(0, e.getMessage());
        }
        apiCallback.onCompleted();
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccess(t);
    }
}
