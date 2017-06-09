package com.github.wcquan.library.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import com.github.wcquan.library.component.InitializeService;
import com.github.wcquan.library.di.component.AppComponent;
import com.github.wcquan.library.di.component.DaggerAppComponent;
import com.github.wcquan.library.di.module.AppModule;
import com.github.wcquan.library.util.DeviceUtil;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by WCQUAN on 2017-06-01.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static AppComponent appComponent;
    private Set<Activity> allActivities;

    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化屏幕宽高
        DeviceUtil.init(this);

        //在子线程中完成其他初始化
        InitializeService.start(this);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }
}
