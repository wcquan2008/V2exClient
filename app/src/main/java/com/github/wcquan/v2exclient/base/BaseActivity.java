package com.github.wcquan.v2exclient.base;

import android.support.v7.app.AppCompatDelegate;

import com.github.wcquan.library.app.BaseApplication;
import com.github.wcquan.library.base.BasePresenter;
import com.github.wcquan.library.base.BaseView;
import com.github.wcquan.library.base.SimpleActivity;
import com.github.wcquan.library.util.ToastUtil;
import com.github.wcquan.v2exclient.di.component.ActivityComponent;
import com.github.wcquan.v2exclient.di.component.DaggerActivityComponent;
import com.github.wcquan.v2exclient.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by WCQUAN on 2017-06-01.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showSuccessMsg(String msg) {
        ToastUtil.showSuccess(mContext, msg);
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.showFail(mContext, msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}