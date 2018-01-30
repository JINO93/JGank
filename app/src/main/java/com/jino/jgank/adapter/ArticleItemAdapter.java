package com.jino.jgank.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jino.jgank.R;
import com.jino.jgank.db.ArticleDao;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.model.bean.ArticleItemDao;
import com.jino.jgank.utils.GlideUtils;
import com.jino.jgank.utils.TimeUtils;

import java.util.List;

import timber.log.Timber;

/**
 * Created by JINO on 2018/1/25.
 */

public class ArticleItemAdapter extends BaseQuickAdapter<ArticleItem, BaseViewHolder> {

    private Fragment mFragment;

    public ArticleItemAdapter(Fragment fragment) {
        super(R.layout.item_gank_category);
        mFragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItem item) {
        helper.setText(R.id.tv_title, item.getDesc());
        if (ArticleDao.get(item.getUrl()) != null) {
            helper.setTextColor(R.id.tv_title, Color.GRAY);
        }
        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_time, item.getPublishTime());
        ImageView imageView = (ImageView) helper.getView(R.id.img_content);
        if (TextUtils.isEmpty(item.getImg())) {
            imageView.setImageResource(R.drawable.ic_android);
            return;
        }
        Timber.i("img url:%s", item.getImg());
        GlideUtils.loadImage(mFragment, imageView, item.getImg());
    }
}
