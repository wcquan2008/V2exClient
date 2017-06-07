package com.github.wcquan.v2exclient.di.component;

import android.app.Activity;

import com.github.wcquan.library.di.component.AppComponent;
import com.github.wcquan.library.di.scope.ActivityScope;
import com.github.wcquan.v2exclient.di.module.ActivityModule;

import dagger.Component;
import com.github.wcquan.v2exclient.view.main.activity.MainActivity;
import com.github.wcquan.v2exclient.view.main.activity.TopicDetailActivity;
import com.github.wcquan.v2exclient.view.system.activity.SettingActivity;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(TopicDetailActivity topicDetailActivity);

    void inject(SettingActivity settingActivity);
}
