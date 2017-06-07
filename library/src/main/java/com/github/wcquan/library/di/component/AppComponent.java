package com.github.wcquan.library.di.component;

import com.github.wcquan.library.app.BaseApplication;
import com.github.wcquan.library.db.RealmHelper;
import com.github.wcquan.library.di.module.AppModule;
import com.github.wcquan.library.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

import com.github.wcquan.library.http.RetrofitHelper;
import com.github.wcquan.library.model.DataManager;
import com.github.wcquan.library.model.pref.PreferencesHelper;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    BaseApplication getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    PreferencesHelper preferencesHelper(); //提供sp帮助类
}
