package com.github.wcquan.library.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class BaseFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<? extends Fragment> mFragments;

    private String[] mTabs;

    public BaseFragmentPagerAdapter(FragmentManager fm,
                                     List<? extends Fragment> fragments,
                                     String[] tabs) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments = fragments;
        mTabs = tabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }

}

