package com.cnbeta.cnbetaone.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.ipresenter.MainActivityPresenter;
import com.cnbeta.cnbetaone.iview.MainActivityContract;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainActivityContract.IView, View.OnClickListener {
    @Inject
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_request).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    protected void onPause() {
        mPresenter.dropView();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_request:
                mPresenter.doRequest();
                break;
        }
    }
}
