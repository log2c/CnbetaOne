package com.cnbeta.cnbetaone.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.databinding.ItemArticleListBinding;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListVH> {
    @Nullable
    private List<ArticleSummary> mArticleSummaryList;
    private Context mContext;

    public ArticleListAdapter(@Nullable List<ArticleSummary> articleSummaryList, Context context) {
        mArticleSummaryList = articleSummaryList;
        mContext = context;
    }

    @NonNull
    @Override
    public ArticleListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_list, parent, false);
        return new ArticleListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListVH holder, int position) {
        if (mArticleSummaryList != null) {
            holder.bindTo(mArticleSummaryList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mArticleSummaryList == null ? 0 : mArticleSummaryList.size();
    }

    static class ArticleListVH extends RecyclerView.ViewHolder {
        ItemArticleListBinding mBinding;

        ArticleListVH(View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        void bindTo(ArticleSummary articleSummary) {
            mBinding.setArticle(articleSummary);
            mBinding.executePendingBindings();
        }
    }
}
