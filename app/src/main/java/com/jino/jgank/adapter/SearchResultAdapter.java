package com.jino.jgank.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jino.jgank.R;
import com.jino.jgank.model.bean.ArticleItem;

import java.util.List;

/**
 * Created by JINO on 2018/2/5.
 */

public class SearchResultAdapter extends BaseQuickAdapter<ArticleItem, BaseViewHolder> {

    public SearchResultAdapter(@Nullable List<ArticleItem> data) {
        super(R.layout.item_search_result, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItem item) {
        helper.setText(R.id.tv_title, item.getDesc());
        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_date, item.getPublishTime());
        helper.setText(R.id.tv_cate, item.getCategory());
    }
}
