package com.github.wcquan.v2exclient.presenter;

import com.github.wcquan.library.base.RxPresenter;
import com.github.wcquan.library.model.DataManager;
import com.github.wcquan.v2exclient.view.system.contract.SettingContract;

import javax.inject.Inject;

/**
 * Created by WCQUAN on 2017-06-05.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public SettingPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public boolean getNightModeState() {
        return mDataManager.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean b) {
       mDataManager.setNightModeState(b);
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mDataManager.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mDataManager.setAutoCacheState(state);
    }

}
