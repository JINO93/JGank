package com.jino.jgank.db;

import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.model.bean.ArticleItemDao;

import java.util.List;

/**
 * Created by JINO on 2018/1/30.
 */

public class ArticleDao {
    public static void addArticle(ArticleItem articleItem, int type) {
        ArticleItem exist = get(articleItem.getUrl());
        if (exist != null) {
            exist.setType(exist.getType() | type);
            DBHelper.getArticleDao().insertOrReplace(articleItem);
            return;
        }
        articleItem.setType(type);
        DBHelper.getArticleDao().insert(articleItem);
    }

    public static void removeArticle(ArticleItem articleItem) {
        DBHelper.getArticleDao().delete(articleItem);
    }

    public static ArticleItem get(String url) {
        return DBHelper.getArticleDao()
                .queryBuilder()
                .where(ArticleItemDao.Properties.Url.eq(url))
                .unique();
    }

    public static List<ArticleItem> getAll(int type) {
        return DBHelper.getArticleDao().queryBuilder()
                .where(ArticleItemDao.Properties.Type.eq(type))
                .list();
    }

    public static void deleteAll(int type) {
        DBHelper.getArticleDao().queryBuilder()
                .where(ArticleItemDao.Properties.Type.eq(type))
                .buildDelete().executeDeleteWithoutDetachingEntities();
    }
}
