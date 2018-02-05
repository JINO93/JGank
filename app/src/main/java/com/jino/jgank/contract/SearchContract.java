package com.jino.jgank.contract;

import com.jino.baselibrary.interfaces.IView;
import com.jino.baselibrary.presenter.IPresenter;
import com.jino.jgank.model.bean.ArticleItem;

import java.util.Collection;
import java.util.List;

/**
 * Created by JINO on 2018/2/5.
 */

public interface SearchContract {
    interface View extends IView {
        void showResult(Collection<ArticleItem> datas);

        void showMore(Collection<ArticleItem> datas);

        void showEmpty();

        void noMoreData();

    }

    interface Presenter extends IPresenter<View> {
        void searchKeyword(String kw);

        void getMoreData(String kw);
    }
}
