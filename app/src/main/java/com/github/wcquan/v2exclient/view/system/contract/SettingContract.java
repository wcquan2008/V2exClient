package com.github.wcquan.v2exclient.view.system.contract;

import com.github.wcquan.library.base.BasePresenter;
import com.github.wcquan.library.base.BaseView;

/**
 * Created by WCQUAN on 2017-06-05.
 */

public interface SettingContract {
    interface View extends BaseView {


    }

    interface Presenter extends BasePresenter<SettingContract.View> {

        boolean getNightModeState();

        void setNightModeState(boolean b);

        boolean getNoImageState();

        void setNoImageState(boolean state);

        boolean getAutoCacheState();

        void setAutoCacheState(boolean state);

    }
}
