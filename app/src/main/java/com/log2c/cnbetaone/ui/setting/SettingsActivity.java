package com.log2c.cnbetaone.ui.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.log2c.cnbetaone.R;
import com.log2c.cnbetaone.base.BaseActivity;
import com.log2c.cnbetaone.di.scope.ActivityScoped;
import com.log2c.cnbetaone.util.ActivityUtils;

import javax.inject.Inject;

@ActivityScoped
public class SettingsActivity extends BaseActivity implements SettingsActivityContract.View {
    @Inject
    SettingsActivityContract.Presenter mPresenter;
    @Inject
    SettingsFragment mSettingsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setTitle(null);
        }
        SettingsFragment settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (settingsFragment == null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mSettingsFragment, R.id.fl_content);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
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

}
