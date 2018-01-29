package com.jino.jgank.utils;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.jino.jgank.R;

/**
 * 封装Glide的工具类
 * Created by JINO on 2018/1/29.
 */

public class GlideUtils {


    public static void loadImage(Fragment context, ImageView view, String url, int placeholderPic, int errorPic) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .placeholder(placeholderPic)
                .error(errorPic);
        Glide.with(context)
                .load(url)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .into(view);
    }


    public static void loadImage(Fragment context, ImageView view, String url) {
        loadImage(context, view, url, R.drawable.ic_android, R.drawable.ic_error);
    }
}
