package com.jino.jgank.view.activity;

import android.widget.TextView;

import com.jino.baselibrary.RequestManager;
import com.jino.baselibrary.base.activity.BaseActivity;
import com.jino.baselibrary.di.component.AppComponent;
import com.jino.baselibrary.interfaces.IPresenter;
import com.jino.jgank.R;
import com.jino.jgank.di.component.DaggerActivityComponent;
import com.jino.jgank.entity.GankResponEntity;
import com.jino.jgank.model.GankCategoryModel;
import com.jino.jgank.net.GankService;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.observers.DisposableObserver;
import io.rx_cache2.Reply;
import timber.log.Timber;

public class MainActivity extends BaseActivity<MainActivity.MyPresenter> {

    @Inject
    public GankCategoryModel model;

    @Inject
    public RequestManager manager;

    @BindView(R.id.tv_show)
    public TextView mTvShow;

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
                .appComponent(component).build()
                .inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.tag("JINO");
        Timber.d("enter initView");
        model.execute(new DisposableObserver<Reply<GankResponEntity>>() {
            @Override
            public void onNext(Reply<GankResponEntity> gankResponEntityReply) {
                Timber.d("get response");
                mTvShow.setText(gankResponEntityReply.getData().toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, new GankCategoryModel.PARAMS(GankService.CATEGROY_ALL, 20, 1));
    }

    @Override
    public void initView() {

    }


    public class MyPresenter implements IPresenter {

        @Override
        public void onAttach() {

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onResume() {

        }

        @Override
        public void onPause() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onDetach() {

        }

        @Override
        public void onDestroy() {

        }
    }
}
