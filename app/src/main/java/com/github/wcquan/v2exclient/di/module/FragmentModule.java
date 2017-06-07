package com.github.wcquan.v2exclient.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.github.wcquan.library.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
