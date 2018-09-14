package com.cnbeta.cnbetaone.base;


public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}