package com.cnbeta.cnbetaone.ui.detail;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.base.BaseFragment;
import com.cnbeta.cnbetaone.di.scope.ActivityScoped;
import com.cnbeta.cnbetaone.entity.ArticleContent;
import com.cnbeta.cnbetaone.util.JSBridgeInterface;
import com.qmuiteam.qmui.widget.QMUIEmptyView;

import javax.inject.Inject;

@ActivityScoped
public class ArticleDetailFragment extends BaseFragment implements ArticleDetailFragmentContract.View {

    @Inject
    ArticleDetailFragmentContract.Presenter mPresenter;
    private QMUIEmptyView mQMUIEmptyView;
    private WebView mWebView;

    @Inject
    public ArticleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(null);
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setHomeButtonEnabled(true);
                supportActionBar.setTitle(null);
            }
        }
        mWebView = view.findViewById(R.id.webView);
        mQMUIEmptyView = view.findViewById(R.id.qmui_empty_view);
        WebView.setWebContentsDebuggingEnabled(true);
        WebSettings wSet = mWebView.getSettings();
        wSet.setJavaScriptEnabled(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_article_detail_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //监听左上角的返回箭头
        if (item.getItemId() == android.R.id.home && getActivity() != null) {
            getActivity().finish();
            return true;
        }
        switch (item.getItemId()) {
            case R.id.ic_explore:
                if (mPresenter != null) {
                    mPresenter.openWithBrowser(getContext());
                }
                break;
            case R.id.ic_refresh:
                if (mPresenter != null) {
                    mPresenter.reload();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.takeView(this);
        }
        mWebView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.dropView();
        }
        mWebView.onPause();
    }

    @Override
    public void showArgumentsError() {
        Toast.makeText(getContext(), R.string.parameter_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getTitle() {
        if (getArguments() == null) {
            return null;
        }
        return getArguments().getString(ArticleDetailActivity.FLAG_TITLE);
    }

    @Override
    public long getSid() {
        if (getArguments() == null) {
            return -1;
        }
        return getArguments().getLong(ArticleDetailActivity.FLAG_SID, -1);
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void loadPage(ArticleContent articleContent) {
        JSBridgeInterface jsBridgeInterface = new JSBridgeInterface(articleContent, getContext());
        mWebView.addJavascriptInterface(jsBridgeInterface, "article_interface");
        //加载本地HTML页面
        mWebView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void showLoadingView() {
        mQMUIEmptyView.setVisibility(View.VISIBLE);
        mQMUIEmptyView.show(true);
    }

    @Override
    public void showReloadView() {
        mQMUIEmptyView.show(false, null, null, getString(R.string.reload), v -> {
            if (mPresenter != null) {
                mPresenter.reload();
            }
        });
    }

    @Override
    public void hideEmptyView() {
        mQMUIEmptyView.setVisibility(View.INVISIBLE);
        mQMUIEmptyView.hide();
    }
}
