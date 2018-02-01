package com.jino.jgank.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jino.jgank.R;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.utils.GlideUtils;
import com.jino.jgank.adapter.callback.DeleteItemCallBack;

import java.util.List;

import timber.log.Timber;

/**
 * Created by JINO on 2018/2/1.
 */

public class HistroyItemAdapter extends BaseQuickAdapter<ArticleItem, BaseViewHolder> {

    private Activity mContext;

    public HistroyItemAdapter(Activity context, @Nullable List<ArticleItem> data) {
        super(R.layout.item_history, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItem item) {
        helper.setText(R.id.tv_title, item.getDesc());
        helper.setText(R.id.tv_time, item.getPublishTime());
        helper.setText(R.id.tv_cate, item.getCategory());
        ImageView imageView = (ImageView) helper.getView(R.id.img_preview);
        if (TextUtils.isEmpty(item.getImg())) {
            imageView.setImageResource(R.drawable.ic_android);
            return;
        }
        Timber.i("img url:%s", item.getImg());
        GlideUtils.loadImage(mContext, imageView, item.getImg());
    }

}
