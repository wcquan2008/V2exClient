package com.github.wcquan.v2exclient.di.component;

import android.app.Activity;

import com.github.wcquan.library.di.component.AppComponent;
import com.github.wcquan.library.di.scope.FragmentScope;
import com.github.wcquan.v2exclient.di.module.FragmentModule;
//import com.codeest.geeknews.ui.gank.fragment.GirlFragment;
//import com.codeest.geeknews.ui.gank.fragment.TechFragment;
//import com.codeest.geeknews.ui.gold.fragment.GoldMainFragment;
//import com.codeest.geeknews.ui.gold.fragment.GoldPagerFragment;
//import com.codeest.geeknews.ui.main.fragment.LikeFragment;
//import com.codeest.geeknews.ui.main.fragment.SettingFragment;
//import com.codeest.geeknews.ui.vtex.fragment.VtexPagerFragment;
//import com.codeest.geeknews.ui.wechat.fragment.WechatMainFragment;
//import com.codeest.geeknews.ui.zhihu.fragment.CommentFragment;
//import com.codeest.geeknews.ui.zhihu.fragment.DailyFragment;
//import com.codeest.geeknews.ui.zhihu.fragment.HotFragment;
//import com.codeest.geeknews.ui.zhihu.fragment.SectionFragment;
//import com.codeest.geeknews.ui.zhihu.fragment.ThemeFragment;

import dagger.Component;

import com.github.wcquan.v2exclient.view.main.fragment.TopicListFragment;

/**
 * Created by WCQUAN on 2017-06-01.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(TopicListFragment topicListFragment);
}
