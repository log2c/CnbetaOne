package com.cnbeta.cnbetaone.ui.detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.base.BaseFragment;
import com.cnbeta.cnbetaone.di.scope.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class ArticleDetailFragment extends BaseFragment implements ArticleDetailFragmentContract.View {

    @Inject
    ArticleDetailFragmentContract.Presenter mPresenter;

    @Inject
    public ArticleDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_detail, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.takeView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.dropView();
        }
    }

    @Override
    public void showArgumentsError() {
        Toast.makeText(getContext(), R.string.parameter_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getTopicId() {
        if (getArguments() == null) {
            return null;
        }
        return getArguments().getString(ArticleDetailActivity.FLAG_TOPIC_ID);
    }

    @Override
    public long getSid() {
        if (getArguments() == null) {
            return -1;
        }
        return getArguments().getLong(ArticleDetailActivity.FLAG_SID, -1);
    }
}
