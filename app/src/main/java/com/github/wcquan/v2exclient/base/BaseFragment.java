package com.github.wcquan.v2exclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.wcquan.library.app.BaseApplication;
import com.github.wcquan.library.base.BasePresenter;
import com.github.wcquan.library.base.BaseView;
import com.github.wcquan.library.base.SimpleFragment;
import com.github.wcquan.library.util.ToastUtil;
import com.github.wcquan.v2exclient.di.component.DaggerFragmentComponent;
import com.github.wcquan.v2exclient.di.component.FragmentComponent;
import com.github.wcquan.v2exclient.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Created by WCQUAN on 2017-06-01.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
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