package com.jino.jgank.adapter.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jino.jgank.R;
import com.jino.jgank.db.DBManager;
import com.jino.jgank.model.bean.ArticleItem;

import java.util.List;

import timber.log.Timber;

/**
 * Created by JINO on 2018/2/1.
 */

public class DeleteItemCallBack extends ItemTouchHelper.Callback {

    private List<ArticleItem> mData;
    private RecyclerView.Adapter mAdapter;

    public DeleteItemCallBack(List<ArticleItem> data, RecyclerView.Adapter adapter) {
        mData = data;
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        ArticleItem removeItem = mData.remove(pos);
        //更新数据库
        DBManager.getInstance().deleteArticleItem(ArticleItem.TYPE_HISTORY, removeItem);
        mAdapter.notifyItemRemoved(pos);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setScrollX(0);
        ((BaseViewHolder) viewHolder).setVisible(R.id.img_delete, false);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            Timber.tag("JINO").i("scrollX:%f--scrollY:%f", dX, dY);
            if (Math.abs(viewHolder.itemView.getScrollX()) < getHideViewWidth(viewHolder)) {
                viewHolder.itemView.scrollTo((int) -dX, 0);
            }
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    private int getHideViewWidth(RecyclerView.ViewHolder viewHolder) {
        ViewGroup itemView = (ViewGroup) viewHolder.itemView;
        View child = itemView.getChildAt(1);
        if (child == null) return 0;
        return child.getWidth();
    }
}
