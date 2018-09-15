package com.cnbeta.cnbetaone.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.ipresenter.ArticleListFragmentPresenter;
import com.cnbeta.cnbetaone.iview.ArticleListContract;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * 显示 Article 列表
 */
@ActivityScoped
public class ArticleListFragment extends DaggerFragment implements ArticleListContract.View {
    public static final String TOPIC_ID = "topic_id";
    @Nullable
    private String mTopicId;
    private RecyclerView mRvArticleList;
    @Inject
    ArticleListFragmentPresenter mPresenter;

    @Inject
    public ArticleListFragment() {
    }

    public static ArticleListFragment newInstance(@Nullable String topicId) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putString(TOPIC_ID, topicId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTopicId = getArguments().getString(TOPIC_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        mRvArticleList = view.findViewById(R.id.rv_article_list);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.dropView();
    }
}
