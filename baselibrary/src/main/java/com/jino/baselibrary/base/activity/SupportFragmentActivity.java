package com.jino.baselibrary.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jino.baselibrary.interfaces.IPresenter;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 含有管理fragment的功能
 * Created by JINO on 2018/1/14.
 */

public abstract class SupportFragmentActivity extends AppCompatActivity implements ISupportActivity {

    final SupportActivityDelegate delegate = new SupportActivityDelegate(this);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        delegate.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        delegate.onPostCreate(savedInstanceState);
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        delegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return delegate;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return delegate.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return delegate.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        delegate.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return delegate.onCreateFragmentAnimator();
    }

    @Override
    public void post(Runnable runnable) {
        delegate.post(runnable);
    }

    @Override
    public void onBackPressedSupport() {
        delegate.onBackPressedSupport();
    }
}
