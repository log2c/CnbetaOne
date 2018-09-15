package com.cnbeta.cnbetaone.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.ipresenter.MainActivityPresenter;
import com.cnbeta.cnbetaone.iview.MainActivityContract;
import com.cnbeta.cnbetaone.ui.fragment.ArticleListFragment;
import com.cnbeta.cnbetaone.util.ActivityUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainActivityContract.IView, View.OnClickListener {
    @Inject
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArticleListFragment allFragment = (ArticleListFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (allFragment == null) {
            allFragment = new ArticleListFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), allFragment, R.id.fl_content);
        }
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
    }
}
