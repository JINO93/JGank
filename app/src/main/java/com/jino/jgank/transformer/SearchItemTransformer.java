package com.jino.jgank.transformer;

import com.jino.baselibrary.di.scope.PreActivity;
import com.jino.baselibrary.transformer.BaseTransformer;
import com.jino.jgank.entity.GankSearchEntity;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.utils.TimeUtils;

import javax.inject.Inject;

/**
 * Created by JINO on 2018/2/5.
 */
@PreActivity
public class SearchItemTransformer extends BaseTransformer<GankSearchEntity.SearchItemBean, ArticleItem> {

    @Inject
    public SearchItemTransformer() {
    }

    @Override
    public ArticleItem transform(GankSearchEntity.SearchItemBean searchItemBean) {
        ArticleItem articleItem = new ArticleItem();
        articleItem.setCategory(searchItemBean.getType());
        articleItem.setUrl(searchItemBean.getUrl());
        articleItem.setPublishTime(TimeUtils.convertSearchItemDateToStandarTime(searchItemBean.getPublishedAt()));
        articleItem.setDesc(searchItemBean.getDesc());
        articleItem.setAuthor(searchItemBean.getWho());
        return articleItem;
    }
}
