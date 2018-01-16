package com.jino.baselibrary.interfaces;

/**
 * Created by JINO on 2018/1/13.
 */

/**
 * View的基础接口
 */
public interface IView {

    void onLoading();

    void onLoadFailed();

    void onLoadSucceed();
}
