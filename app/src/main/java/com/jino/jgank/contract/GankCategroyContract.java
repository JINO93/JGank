package com.jino.jgank.contract;

import com.jino.baselibrary.interfaces.IView;
import com.jino.baselibrary.presenter.IPresenter;
import com.jino.jgank.entity.GankResponEntity;

import java.util.List;

/**
 * Created by JINO on 2018/1/24.
 */

public interface GankCategroyContract {
    public interface View extends IView {
        void loadMore(List<GankResponEntity.GankItemBean> items);

        void refresh(List<GankResponEntity.GankItemBean> items);


    }

    public interface Presenter extends IPresenter<View> {
        void loadData(String cate);

        void loadMoreData(String cate);
    }
}
