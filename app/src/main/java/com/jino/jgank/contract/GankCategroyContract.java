package com.jino.jgank.contract;

import com.jino.baselibrary.interfaces.IView;
import com.jino.baselibrary.presenter.IPresenter;

/**
 * Created by JINO on 2018/1/24.
 */

public interface GankCategroyContract {
    public interface View extends IView {
        void loadMore();

        void refresh();
    }

    public interface Presenter extends IPresenter<View> {
        void loadData(String cate);

        void loadMoreData(String cate);
    }
}
