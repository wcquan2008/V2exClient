package com.github.wcquan.library.di.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.github.wcquan.library.app.BaseApplication;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@Module(includes = {
        SPModule.class,
        HttpModule.class,
        DBModule.class
})
public class AppModule {
    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplication() {
        return application;
    }
}
