package com.log2c.cnbetaone.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.log2c.cnbetaone.R;
import com.log2c.cnbetaone.base.BaseActivity;
import com.log2c.cnbetaone.ui.setting.SettingsActivity;
import com.log2c.cnbetaone.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainActivityContract.View, android.view.View.OnClickListener {
    @Inject
    MainActivityPresenter mPresenter;
    private List<String> mTopicTypeList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTopicTypeList = new ArrayList<>();
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

    /**
     * // TODO 多Topic阅读模式
     *
     * @return topictype
     */
    public String getTopic() {
        if (mTopicTypeList.isEmpty()) {
            return null;
        }
        return mTopicTypeList.get(0);
    }
}
