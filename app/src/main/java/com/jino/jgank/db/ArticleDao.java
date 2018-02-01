package com.jino.jgank.db;

import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.model.bean.ArticleItemDao;

import java.util.List;

/**
 * Created by JINO on 2018/1/30.
 */

public class ArticleDao {
    /**
     * 添加或修改数据
     *
     * @param articleItem 要添加的数据
     * @param type        类型
     */
    public static void addArticle(ArticleItem articleItem, int type) {
        ArticleItem exist = get(articleItem.getUrl());
        if (exist != null) {
            articleItem.setType(exist.getType() | type);
            DBHelper.getArticleDao().insertOrReplace(articleItem);
            return;
        }
        articleItem.setType(type);
        DBHelper.getArticleDao().insert(articleItem);
    }

    public static void addArticle(ArticleItem articleItem) {
        DBHelper.getArticleDao().insertOrReplace(articleItem);
    }

    public static void removeArticle(ArticleItem articleItem) {
        DBHelper.getArticleDao().delete(articleItem);
    }

    /**
     * 获取指定的数据
     *
     * @param url
     * @return
     */
    public static ArticleItem get(String url) {
        return DBHelper.getArticleDao()
                .queryBuilder()
                .where(ArticleItemDao.Properties.Url.eq(url))
                .unique();
    }

    /**
     * 获取指定类型的指定数据
     *
     * @param url  要查找数据的url
     * @param type 类型
     * @return
     */
    public static ArticleItem get(String url, int type) {
        ArticleItem item = get(url);
        if (item == null || (item.getType() & type) != type) return null;
        return item;
    }

    /**
     * 获取指定类型的全部数据
     *
     * @param type 类型
     * @return
     */
    public static List<ArticleItem> getAll(int type) {
        return DBHelper.getArticleDao().queryBuilder()
                .where(ArticleItemDao.Properties.Type.eq(type))
                .list() ;
    }

    public static void deleteAll(int type) {
        DBHelper.getArticleDao().queryBuilder()
                .where(ArticleItemDao.Properties.Type.eq(type))
                .buildDelete().executeDeleteWithoutDetachingEntities();
    }
}