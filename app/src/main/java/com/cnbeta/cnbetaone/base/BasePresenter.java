package com.cnbeta.cnbetaone.base;


import android.support.annotation.NonNull;

public interface BasePresenter<T> {

    void takeView(@NonNull T view);

    void dropView();

}