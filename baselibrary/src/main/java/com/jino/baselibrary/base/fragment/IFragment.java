package com.jino.baselibrary.base.fragment;

import android.view.View;

/**
 * Fragment基接口
 * Created by JINO on 2018/1/18.
 */

public interface IFragment {
    int getLayoutId();

    void initView(View view);

    void attachView(View view);

}
