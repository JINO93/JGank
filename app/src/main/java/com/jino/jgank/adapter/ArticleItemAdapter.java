package com.jino.jgank.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jino.jgank.R;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.utils.TimeUtils;

import java.util.List;

/**
 * Created by JINO on 2018/1/25.
 */

public class ArticleItemAdapter extends BaseQuickAdapter<GankResponEntity.GankItemBean, BaseViewHolder> {

    private Fragment mFragment;

    public ArticleItemAdapter(Fragment fragment) {
        super(R.layout.item_gank_category);
        mFragment = fragment;
    }

    @Override
    protected void convert(BaseViewHolder helper, GankResponEntity.GankItemBean item) {
        helper.setText(R.id.tv_title, item.getDesc());
        helper.setText(R.id.tv_author, item.getWho());
        helper.setText(R.id.tv_time, TimeUtils.convertUTCToStandarTime(item.getPublishedAt()));
        ImageView imageView = (ImageView) helper.getView(R.id.img_content);
        if (item.getImages() == null || item.getImages().size() == 0) {
            imageView.setVisibility(View.GONE);
            return;
        }
        Glide.with(mFragment).load(item.getImages().get(0))
                .into(imageView);
    }
}
