package com.cnbeta.cnbetaone.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.base.BaseActivity;
import com.cnbeta.cnbetaone.ui.setting.SettingsActivity;
import com.cnbeta.cnbetaone.util.ActivityUtils;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainActivityContract.View, android.view.View.OnClickListener {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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
