package com.cnbeta.cnbetaone.ui.main;


import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.adapter.ArticleListAdapter;
import com.cnbeta.cnbetaone.base.BaseFragment;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

import javax.inject.Inject;

/**
 * 显示 Article 列表
 */
public class ArticleListFragment extends BaseFragment implements ArticleListFragmentContract.View {
    public static final String TOPIC_ID = "topic_id";
    private RecyclerView mRecyclerView;
    @Nullable
    @Inject
    ArticleListFragmentContract.Presenter mPresenter;
    private ArticleListAdapter mArticleListAdapter;
    //    private QMUIEmptyView mQMUIEmptyView;
    private SwipeRefreshLayout mSwipeRefreshView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        mRecyclerView = view.findViewById(R.id.rv_article_list);
//        mQMUIEmptyView = view.findViewById(R.id.qmui_empty_view);
        mSwipeRefreshView = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshView.setColorSchemeResources(R.color.app_color_theme_1);
        mSwipeRefreshView.setOnRefreshListener(() -> {
            if (mPresenter != null) {
                mPresenter.reloadData(ArticleListFragment.this);
            }
        });
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(view.findViewById(R.id.toolbar));
        }
        return view;
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
    public void initAdapter(LiveData<PagedList<ArticleSummary>> summaryList) {
        mArticleListAdapter = new ArticleListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        summaryList.observe(this, pagedList -> mArticleListAdapter.submitList(pagedList));
        mRecyclerView.setAdapter(mArticleListAdapter);
    }

    @Override
    public void onDataLoaded() {
        mSwipeRefreshView.setRefreshing(false);
    }

    @Override
    public void showEmptyView() {
//        mQMUIEmptyView.show();
    }

    @Override
    public void hideEmptyView() {
//        mQMUIEmptyView.hide();
    }
}
