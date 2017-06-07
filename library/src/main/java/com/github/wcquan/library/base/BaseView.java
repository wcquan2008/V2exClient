package com.github.wcquan.library.base;

/**
 * Created by WCQUAN on 2017-06-01.
 * View基类
 */
public interface BaseView {

    void showSuccessMsg(String msg);

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
