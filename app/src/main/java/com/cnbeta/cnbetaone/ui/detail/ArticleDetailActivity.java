package com.cnbeta.cnbetaone.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.base.BaseActivity;
import com.cnbeta.cnbetaone.util.ActivityUtils;

import javax.inject.Inject;

public class ArticleDetailActivity extends BaseActivity {
    public static final String FLAG_TOPIC_ID = "topic_id";
    public static final String FLAG_SID = "sid";

    @Inject
    ArticleDetailFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        String topicId = getIntent().getStringExtra(FLAG_TOPIC_ID);
        long sid = getIntent().getLongExtra(FLAG_SID, -1);
        if (TextUtils.isEmpty(topicId) || topicId.equals("null") || sid == -1) {
            Toast.makeText(getApplicationContext(), R.string.parameter_error, Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        ArticleDetailFragment detailFragment = (ArticleDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fl_content);
        if (detailFragment == null) {
            Bundle args = new Bundle();
            args.putLong(ArticleDetailActivity.FLAG_SID, sid);
            args.putString(ArticleDetailActivity.FLAG_TOPIC_ID, topicId);
            mDetailFragment.setArguments(args);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mDetailFragment, R.id.fl_content);
        } else {
            mDetailFragment = detailFragment;
        }
    }
}
