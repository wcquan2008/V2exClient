package com.github.wcquan.v2exclient.view.main.contract;

import com.tbruyelle.rxpermissions2.RxPermissions;

import com.github.wcquan.library.base.BasePresenter;
import com.github.wcquan.library.base.BaseView;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public interface MainContract {
    interface View extends BaseView {

    }

    interface  Presenter extends BasePresenter<View> {

        void checkPermissions(RxPermissions rxPermissions);

        void setNightModeState(boolean b);

    }
}
