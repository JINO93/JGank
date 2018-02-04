package com.jino.jgank.view.activity;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageButton;

import com.jino.baselibrary.base.activity.BaseActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.jgank.R;
import com.jino.jgank.adapter.HistroyItemAdapter;
import com.jino.jgank.adapter.callback.DeleteItemCallBack;
import com.jino.jgank.db.DBManager;
import com.jino.jgank.di.component.DaggerActivityComponent;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.view.widget.ChangeableTextView;

import java.util.List;

import butterknife.BindView;

public class HistoryActivity extends BaseActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.btn_sort)
    public ChangeableTextView btn_sort;
    @BindView(R.id.btn_clear)
    public ImageButton btn_clear;
    @BindView(R.id.rv_history)
    public RecyclerView list_history;
    private List<ArticleItem> historyItem;
    private HistroyItemAdapter adapter;

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadFailed() {

    }

    @Override
    public void onLoadSucceed() {

    }

    @Override
    public void injectComponent(AppComponent component) {
        DaggerActivityComponent.builder()
                .appComponent(component)
                .build()
                .inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("浏览历史");
        setupToolBar(mToolbar);
        adapter = new HistroyItemAdapter(this, historyItem);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new DeleteItemCallBack(historyItem, adapter));
        itemTouchHelper.attachToRecyclerView(list_history);
        list_history.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        list_history.setAdapter(adapter);
    }

    @Override
    public void initData() {
        historyItem = DBManager.getInstance().getAll(ArticleItem.TYPE_HISTORY);
    }
}
