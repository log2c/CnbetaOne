package com.cnbeta.cnbetaone.rxjava;

import com.cnbeta.cnbetaone.exception.CApiException;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class CApiObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        CApiException cApiException;
        if (e instanceof HttpException) {
            // We had non-2XX http error
            cApiException = new CApiException(((HttpException) e).message(), -1);
        } else if (e instanceof IOException) {
            // A network or conversion error happened
            cApiException = new CApiException(e.getMessage(), -1);
        } else if (e instanceof CApiException) {
            cApiException = (CApiException) e;
        } else {
            cApiException = new CApiException("UNKNOW ERROR!", -2);
        }
        onFail(cApiException);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFail(CApiException e);
}
