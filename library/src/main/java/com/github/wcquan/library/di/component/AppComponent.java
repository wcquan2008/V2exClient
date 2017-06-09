package com.github.wcquan.library.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.wcquan.library.app.BaseApplication;
import com.github.wcquan.library.di.module.AppModule;
import com.github.wcquan.library.model.http.api.V2exService;
import com.github.wcquan.library.model.db.IDBHelper;
import com.github.wcquan.library.model.pref.IPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    BaseApplication app();

    Context appContext();  // 提供App的Context

    SharedPreferences preference();

    Retrofit retrofit();

    OkHttpClient okHttpClient();

    Realm realm();


    V2exService v2exService();     //API类

    IDBHelper realmHelper();    //提供数据库帮助类

    IPreferencesHelper preferencesHelper(); //提供sp帮助类
}
