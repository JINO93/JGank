package com.jino.jgank.adapter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jino.jgank.R;
import com.jino.jgank.db.DBManager;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.utils.GlideUtils;

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
        if (DBManager.getInstance().exist(item, ArticleItem.TYPE_HISTORY)) {
            helper.setTextColor(R.id.tv_title, Color.GRAY);
        }
        helper.setText(R.id.tv_time, item.getAuthor());
        helper.setText(R.id.tv_time, item.getPublishTime());
        ImageView imageView = helper.getView(R.id.img_content);
        if (TextUtils.isEmpty(item.getImg())) {
            imageView.setImageResource(R.drawable.ic_android);
            return;
        }
        Timber.i("img url:%s", item.getImg());
        GlideUtils.loadImage(mFragment, imageView, item.getImg());
    }
}
