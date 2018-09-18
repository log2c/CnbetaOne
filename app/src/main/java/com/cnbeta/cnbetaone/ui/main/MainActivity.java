package com.cnbeta.cnbetaone.ui.main;

import android.os.Bundle;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.util.ActivityUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainActivityContract.View, android.view.View.OnClickListener {
    @Inject
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArticleListFragment allFragment = (ArticleListFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (allFragment == null) {
            allFragment = new ArticleListFragment();
//            Bundle args = new Bundle();
//            args.putString(ArticleListFragment.TOPIC_ID, "9");
//            allFragment.setArguments(args);
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
    public void onClick(android.view.View v) {
    }
}
