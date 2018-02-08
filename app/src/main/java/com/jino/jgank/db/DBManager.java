package com.jino.jgank.db;

import android.content.Context;

import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.model.bean.ArticleItemDao;
import com.jino.jgank.model.bean.DaoMaster;
import com.jino.jgank.model.bean.DaoSession;

import org.greenrobot.greendao.Property;

import java.util.List;

import timber.log.Timber;

/**
 * Created by JINO on 2018/1/30.
 */

public class DBManager {

    private static final String DB_NAME = "JGank.db";

    private static volatile DBManager INSTANCE;
    private static final int VERSION = 1;
    private final DaoSession session;
    private final ArticleItemDao articleItemDao;

    private DBManager(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        session = daoMaster.newSession();
        articleItemDao = session.getArticleItemDao();
        Timber.tag(DBManager.class.getSimpleName());
    }

    public static void init(Context context) {
        if (INSTANCE == null) {
            synchronized (DBManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBManager(context);
                }
            }
        }
    }

    public static DBManager getInstance() {
        return INSTANCE;
    }

    public ArticleItem get(String url) {
        return articleItemDao.load(url);
    }

    public boolean exist(ArticleItem articleItem, int type) {
        ArticleItem load = get(articleItem.getUrl());
        if (load == null) return false;
        return (load.getType() & type) == type;
    }

    public long insertArticleItem(ArticleItem articleItem) {
        return articleItemDao.insert(articleItem);
    }

    public void updataArticleItem(ArticleItem articleItem) {
        articleItemDao.update(articleItem);
    }

    public List<ArticleItem> getAll(int type, int page, int count) {
        return articleItemDao.queryBuilder()
                .where(ArticleItemDao.Properties.Type.in(type, ArticleItem.TYPE_BOTH))
                .offset(count * page)
                .limit(count)
                .build()
                .list();
    }

    public List<ArticleItem> getAll(int type, int page, int count, Property property) {
        return articleItemDao.queryBuilder()
                .where(ArticleItemDao.Properties.Type.in(type, ArticleItem.TYPE_BOTH))
                .offset(count * (page - 1))
                .limit(count)
                .orderDesc(property)
                .build()
                .list();
    }

    public List<ArticleItem> getAll(int type) {
        return articleItemDao.queryBuilder()
                .where(ArticleItemDao.Properties.Type.in(type, ArticleItem.TYPE_BOTH))
                .build()
                .list();
    }

    public void deleteArticleItem(int type, ArticleItem articleItem) {
        if (articleItem.getType() == ArticleItem.TYPE_BOTH) {
            articleItem.setType(articleItem.getType() ^ type);
            Timber.d("remove type %d ,and updata type to %d", type, articleItem.getType());
            updataArticleItem(articleItem);
            return;
        }
        articleItemDao.delete(articleItem);
    }

    public void deleteAll(int type) {
        List<ArticleItem> items = getAll(type);
        for (ArticleItem item : items) {
            deleteArticleItem(type, item);
        }
    }


}
