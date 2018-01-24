package com.jino.baselibrary.presenter;

import com.jino.baselibrary.interfaces.IView;
import com.jino.baselibrary.model.IModel;
import com.jino.baselibrary.utils.ConditionUtils;

import javax.inject.Inject;

/**
 * Created by JINO on 2018/1/24.
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V> {

    protected V mView;
    protected M mModel;

    @Override
    public void onAttach(V view) {
        ConditionUtils.checkNotNull(view, "view can not be null.");
        mView = view;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onDestroy() {
        if (mModel != null) {
            mModel.dispose();
        }
    }
}
