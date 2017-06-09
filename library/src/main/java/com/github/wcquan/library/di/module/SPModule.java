package com.github.wcquan.library.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import com.github.wcquan.library.model.pref.IPreferencesHelper;
import com.github.wcquan.library.model.pref.PreferencesHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by WCQUAN on 2017-06-08.
 */

@Module
public class SPModule {

    @Provides
    @Singleton
    SharedPreferences provideSP(Context context) {
        String applicationId = context.getApplicationContext().getPackageName();
        PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences sharedPrefs;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            sharedPrefs = context.getSharedPreferences(applicationId, Context.MODE_MULTI_PROCESS);
        } else {
            sharedPrefs = context.getSharedPreferences(applicationId, Context.MODE_PRIVATE);
        }
        return sharedPrefs;
    }

    @Provides
    @Singleton
    IPreferencesHelper providePreferencesHelper(SharedPreferences sharedPreferences) {
        return new PreferencesHelper(sharedPreferences);
    }
}
