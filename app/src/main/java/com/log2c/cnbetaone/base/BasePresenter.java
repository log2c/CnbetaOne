package com.log2c.cnbetaone.base;


import android.support.annotation.NonNull;

public interface BasePresenter<T> {

    void takeView(@NonNull T view);

    void dropView();

}