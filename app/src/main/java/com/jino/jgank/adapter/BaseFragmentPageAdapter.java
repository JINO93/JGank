package com.jino.jgank.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JINO on 2018/1/26.
 */

public class BaseFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();

    public BaseFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentPageAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
