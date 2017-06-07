package com.github.wcquan.v2exclient.di.module;

import android.app.Activity;

import com.github.wcquan.library.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
