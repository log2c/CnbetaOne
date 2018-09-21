package com.cnbeta.cnbetaone.ui.detail;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import javax.inject.Inject;

@ActivityScoped
public class ArticleDetailFragment extends BaseFragment implements ArticleDetailFragmentContract.View {

    @Inject
    ArticleDetailFragmentContract.Presenter mPresenter;
    private WebView mWebView;

    @Inject
    public ArticleDetailFragment() {
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        mWebView = view.findViewById(R.id.webView);
        WebView.setWebContentsDebuggingEnabled(true);
        WebSettings wSet = mWebView.getSettings();
        wSet.setJavaScriptEnabled(true);
        return view;
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

    @SuppressLint("JavascriptInterface")
    @Override
    public void loadPage(ArticleContent articleContent) {
        JSBridgeInterface jsBridgeInterface = new JSBridgeInterface(articleContent, getContext());
        mWebView.addJavascriptInterface(jsBridgeInterface, "article_interface");
        //加载本地HTML页面
        mWebView.loadUrl("file:///android_asset/index.html");
    }
}
