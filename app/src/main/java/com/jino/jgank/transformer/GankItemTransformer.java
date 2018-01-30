package com.jino.jgank.transformer;

import com.jino.baselibrary.di.scope.PreFragment;
import com.jino.baselibrary.transformer.BaseTransformer;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.utils.TimeUtils;

import javax.inject.Inject;

/**
 * Created by JINO on 2018/1/30.
 */
@PreFragment
public class GankItemTransformer extends BaseTransformer<GankResponEntity.GankItemBean, ArticleItem> {

    @Inject
    public GankItemTransformer(){

    }

    @Override
    public ArticleItem transform(GankResponEntity.GankItemBean gankItemBean) {
        ArticleItem articleItem = new ArticleItem();
        articleItem.setAuthor(gankItemBean.getWho());
        articleItem.setCategory(gankItemBean.getType());
        articleItem.setDesc(gankItemBean.getDesc());
        articleItem.setPublishTime(TimeUtils.convertUTCToStandarTime(gankItemBean.getPublishedAt()));
        articleItem.setUrl(gankItemBean.getUrl());
        if (gankItemBean.getImages() != null && gankItemBean.getImages().size() != 0) {
            articleItem.setImg(gankItemBean.getImages().get(0));
        }
        articleItem.setType(ArticleItem.TYPE_DEFAULT);
        return articleItem;
    }
}
