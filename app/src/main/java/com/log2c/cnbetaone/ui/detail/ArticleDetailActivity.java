package com.log2c.cnbetaone.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.log2c.cnbetaone.R;
import com.log2c.cnbetaone.base.BaseActivity;
import com.log2c.cnbetaone.util.ActivityUtils;

import javax.inject.Inject;

public class ArticleDetailActivity extends BaseActivity {
    public static final String FLAG_TITLE = "title";
    public static final String FLAG_SID = "sid";

    @Inject
    ArticleDetailFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        String title = getIntent().getStringExtra(FLAG_TITLE);
        long sid = getIntent().getLongExtra(FLAG_SID, -1);
        if (TextUtils.isEmpty(title) || sid == -1) {
            Toast.makeText(getApplicationContext(), R.string.parameter_error, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        ArticleDetailFragment detailFragment = (ArticleDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (detailFragment == null) {
            Bundle args = new Bundle();
            args.putLong(ArticleDetailActivity.FLAG_SID, sid);
            args.putString(ArticleDetailActivity.FLAG_TITLE, title);
            mDetailFragment.setArguments(args);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mDetailFragment, R.id.fl_content);
        } else {
            mDetailFragment = detailFragment;
        }
    }
}
