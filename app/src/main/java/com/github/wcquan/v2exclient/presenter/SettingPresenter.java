package com.github.wcquan.v2exclient.presenter;

import com.github.wcquan.library.base.RxPresenter;
import com.github.wcquan.library.model.pref.IPreferencesHelper;
import com.github.wcquan.v2exclient.view.system.contract.SettingContract;

import javax.inject.Inject;

/**
 * Created by WCQUAN on 2017-06-05.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter{

    IPreferencesHelper mPreferencesHelper;

    @Inject
    public SettingPresenter(IPreferencesHelper preferencesHelper) {
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean b) {
        mPreferencesHelper.setNightModeState(b);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferencesHelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mPreferencesHelper.setAutoCacheState(state);
    }

}
