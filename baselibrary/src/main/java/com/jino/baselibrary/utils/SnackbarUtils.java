package com.jino.baselibrary.utils;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by JINO on 2018/2/6.
 */

public class SnackbarUtils {

    public static void showContent(View parent, String content) {
        Snackbar.make(parent, content, Snackbar.LENGTH_SHORT).show();
    }

    public static void showContent(View parent, @StringRes int resId) {
        Snackbar.make(parent, resId, Snackbar.LENGTH_SHORT).show();
    }


}
