package com.cnbeta.cnbetaone.ipresenter;

import android.support.annotation.Nullable;

import com.cnbeta.cnbetaone.iview.ArticleListContract;
import com.cnbeta.cnbetaone.ui.fragment.ArticleListFragment;

import javax.inject.Inject;

public class ArticleListFragmentPresenter implements ArticleListContract.Presenter {

    @Nullable
    private String topicType;

    @Inject
    public ArticleListFragmentPresenter(ArticleListFragment fragment) {
        if (fragment.getArguments() != null) {
            this.topicType = fragment.getArguments().getString(ArticleListFragment.TOPIC_ID);
        }
    }

    @Override
    public void takeView(ArticleListContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
