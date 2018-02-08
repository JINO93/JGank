package com.jino.jgank.view.activity;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.ImageButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jino.baselibrary.base.activity.BaseActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.jgank.R;
import com.jino.jgank.adapter.HistroyItemAdapter;
import com.jino.jgank.adapter.callback.DeleteItemCallBack;
import com.jino.jgank.db.DBManager;
import com.jino.jgank.di.component.DaggerActivityComponent;
import com.jino.jgank.model.bean.ArticleItem;
import com.jino.jgank.model.bean.ArticleItemDao;
import com.jino.jgank.view.widget.ChangeableTextView;

import org.greenrobot.greendao.Property;

import java.util.List;

import butterknife.BindView;

public class HistoryActivity extends BaseActivity implements ChangeableTextView.OnTextContentChangeListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final int COUNT = 10;

    private int page = 1;

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

    private String[] cateList;
    private Property mSortProperty;

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
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new DeleteItemCallBack(historyItem, adapter));
        itemTouchHelper.attachToRecyclerView(list_history);
        list_history.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        list_history.setAdapter(adapter);
        btn_sort.setTextContentChangeListener(this);
    }

    @Override
    public void initData() {
        cateList = getResources().getStringArray(R.array.sort_category);
        historyItem = DBManager.getInstance().getAll(ArticleItem.TYPE_HISTORY,
                1, COUNT, ArticleItemDao.Properties.LastTime);
    }

    @Override
    public void onContentChange(String content) {
        int index = 0;
        for (; index < cateList.length; index++) {
            if (cateList[index].equals(content))
                break;
        }

        switch (index) {
            case 0:
                mSortProperty = ArticleItemDao.Properties.LastTime;
                break;
            case 1:
                mSortProperty = ArticleItemDao.Properties.PublishTime;
                break;
            case 2:
                mSortProperty = ArticleItemDao.Properties.Category;
                break;
        }
        historyItem = DBManager.getInstance().getAll(ArticleItem.TYPE_HISTORY,
                1, COUNT, mSortProperty);
        adapter.setNewData(historyItem);
//        adapter.setEmptyView();
    }
}
