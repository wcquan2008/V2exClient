package com.github.wcquan.v2exclient.presenter;

import android.Manifest;

import com.github.wcquan.library.model.pref.IPreferencesHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;
import javax.inject.Inject;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import com.github.wcquan.library.base.RxPresenter;
import com.github.wcquan.library.component.RxBus;
import com.github.wcquan.library.model.event.NightModeEvent;
import com.github.wcquan.library.util.RxUtil;
import com.github.wcquan.library.widget.CommonSubscriber;
import com.github.wcquan.v2exclient.view.main.contract.MainContract;

/**
 * Created by WCQUAN on 2017-06-01.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{

    private IPreferencesHelper mPreferencesHelper;

    @Inject
    public MainPresenter(IPreferencesHelper preferencesHelper) {
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(NightModeEvent.class)
                .compose(RxUtil.<NightModeEvent>rxSchedulerHelper())
                .map(new Function<NightModeEvent, Boolean>() {
                    @Override
                    public Boolean apply(NightModeEvent nightModeEvent) {
                        return nightModeEvent.getNightMode();
                    }
                })
                .subscribeWith(new CommonSubscriber<Boolean>(mView, "切换模式失败") {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.useNightMode(aBoolean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        registerEvent();
                    }
                })
        );
    }


    @Override
    public void checkPermissions(RxPermissions rxPermissions) {
        addSubscribe(rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) {
                        if (!granted) {
                            mView.showErrorMsg("该应用需要文件写入权限哦~");
                        }
                    }
                })
        );
    }

    @Override
    public void setNightModeState(boolean b) {
        mPreferencesHelper.setNightModeState(b);
    }

}
