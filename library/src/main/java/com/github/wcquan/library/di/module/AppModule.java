package com.github.wcquan.library.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.github.wcquan.library.app.BaseApplication;
import com.github.wcquan.library.db.IDBHelper;
import com.github.wcquan.library.db.RealmHelper;
import com.github.wcquan.library.http.IHttpHelper;
import com.github.wcquan.library.http.RetrofitHelper;
import com.github.wcquan.library.model.DataManager;
import com.github.wcquan.library.model.pref.IPreferencesHelper;
import com.github.wcquan.library.model.pref.PreferencesHelper;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@Module
public class AppModule {
    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    IHttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    IDBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    IPreferencesHelper providePreferencesHelper(PreferencesHelper preferencesHelper) {
        return preferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(IHttpHelper IHttpHelper, IDBHelper IDBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(IHttpHelper, IDBHelper, preferencesHelper);
    }
}
