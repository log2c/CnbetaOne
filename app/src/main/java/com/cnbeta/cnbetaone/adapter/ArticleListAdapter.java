package com.cnbeta.cnbetaone.adapter;

import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cnbeta.cnbetaone.R;
import com.cnbeta.cnbetaone.databinding.ItemArticleListBinding;
import com.cnbeta.cnbetaone.entity.ArticleSummary;

public class ArticleListAdapter extends PagedListAdapter<ArticleSummary, ArticleListAdapter.ArticleListVH> {


    public ArticleListAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ArticleListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_list, parent, false);
        return new ArticleListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleListVH holder, int position) {
        holder.bindTo(getItem(position));
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

    private static final DiffUtil.ItemCallback<ArticleSummary> diffCallback = new DiffUtil.ItemCallback<ArticleSummary>() {

        @Override
        public boolean areItemsTheSame(ArticleSummary oldItem, ArticleSummary newItem) {
            return oldItem.getSid() == newItem.getSid();
        }

        @Override
        public boolean areContentsTheSame(ArticleSummary oldItem, ArticleSummary newItem) {
            return oldItem.equals(newItem);
        }
    };
}
