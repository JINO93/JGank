package com.jino.jgank.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.jino.jgank.model.bean.ArticleItemDao;
import com.jino.jgank.model.bean.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * 数据库升级帮助类
 * Created by JINO on 2018/2/9.
 */

public class MySQLOpenHelper extends DaoMaster.OpenHelper {
    public MySQLOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.DEBUG = true;
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db,ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db,ifExists);
            }
        }, ArticleItemDao.class);
    }
}
