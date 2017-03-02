package com.example.recyclerviewtest.utils;


import android.support.annotation.NonNull;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxUtils {

    private RxUtils() {
    }

    @NonNull
    public static <T> Observable.Transformer<T,T> async() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
