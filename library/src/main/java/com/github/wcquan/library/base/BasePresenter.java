package com.github.wcquan.library.base;

/**
 * Created by WCQUAN on 2017-06-01.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
