package com.jino.baselibrary.interfaces;

/**
 * Created by JINO on 2018/1/14.
 */

public interface IPresenter {

    void onAttach();

    void onCreate();

    void onResume();

    void onPause();

    void onStop();

    void onDistach();

    void onDestroy();
}
