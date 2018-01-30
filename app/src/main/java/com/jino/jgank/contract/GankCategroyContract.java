package com.jino.jgank.contract;

import com.jino.baselibrary.interfaces.IView;
import com.jino.baselibrary.presenter.IPresenter;
import com.jino.jgank.model.bean.ArticleItem;

import java.util.List;

/**
 * Created by JINO on 2018/1/24.
 */

public interface GankCategroyContract {
    interface View extends IView {
        void loadMore(List<ArticleItem> items);

        void refresh(List<ArticleItem> items);


    }

    interface Presenter extends IPresenter<View> {
        void loadData(String cate);

        void loadMoreData(String cate);
    }
}
