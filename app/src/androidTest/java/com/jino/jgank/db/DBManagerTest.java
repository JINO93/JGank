package com.jino.jgank.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.jino.jgank.model.bean.ArticleItem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import timber.log.Timber;

import static org.junit.Assert.*;

/**
 * Created by JINO on 2018/2/9.
 */
public class DBManagerTest {


    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getContext();
        DBManager.init(context);
    }


    int test = 1000000;

    @Test
    public void insertArticleItem() throws Exception {
//        DBManager.getInstance().deleteAll(ArticleItem.TYPE_DEFAULT);
        ArticleItem item;
        long start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            item = new ArticleItem(i + "", ArticleItem.TYPE_DEFAULT, "url" + i,
                    "", "JINO" + i, "", "", System.currentTimeMillis());
            DBManager.getInstance().insertArticleItem(item);
        }
        Timber.tag("test").d("last %d second.", (System.currentTimeMillis() - start) / 1000);
    }

    @Test
    public void getAll() throws Exception {
        long start = System.currentTimeMillis();
        List<ArticleItem> all = DBManager.getInstance().getAll(ArticleItem.TYPE_DEFAULT);
        assertEquals(all.size(), test);
        Timber.tag("test").d("last %d second.", (System.currentTimeMillis() - start) / 1000);
    }

}