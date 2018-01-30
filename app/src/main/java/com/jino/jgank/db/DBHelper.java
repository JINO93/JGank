package com.jino.jgank.db;

import android.content.Context;

import com.jino.baselibrary.utils.ConditionUtils;
import com.jino.jgank.model.bean.ArticleItemDao;
import com.jino.jgank.model.bean.DaoMaster;
import com.jino.jgank.model.bean.DaoSession;

/**
 * Created by JINO on 2018/1/30.
 */

public class DBHelper {

    private static final String DB_NAME = "JGank.db";

    private static volatile DBHelper INSTANCE;
    private static final int VERSION = 1;
    private final DaoMaster.OpenHelper helper;
    private final DaoMaster daoMaster;
    private final DaoSession session;

    private DBHelper(Context context) {
        helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        daoMaster = new DaoMaster(helper.getWritableDb());
        session = daoMaster.newSession();
    }

    public static void init(Context context) {
        if (INSTANCE == null) {
            synchronized (DBHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DBHelper(context);
                }
            }
        }
    }


    public static ArticleItemDao getArticleDao() {
        ConditionUtils.checkNotNull(INSTANCE, "must be execuate init method first");
        return INSTANCE.session.getArticleItemDao();
    }
}
