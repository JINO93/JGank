package com.jino.baselibrary.presenter;

import com.jino.baselibrary.interfaces.IView;

/**
 * Created by JINO on 2018/1/14.
 */

public interface IPresenter<V extends IView> {

    void onAttach(V view);

    void onResume();

    void onPause();

    void onDetach();

    void onDestroy();
}
