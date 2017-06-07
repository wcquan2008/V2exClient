package com.github.wcquan.v2exclient.view.system.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.wcquan.library.app.Constants;
import com.github.wcquan.library.component.ACache;
import com.github.wcquan.library.component.RxBus;
import com.github.wcquan.library.model.event.NightModeEvent;
import com.github.wcquan.library.util.DialogMessageUtil;
import com.github.wcquan.v2exclient.R;
import com.github.wcquan.v2exclient.base.BaseActivity;
import com.github.wcquan.v2exclient.presenter.SettingPresenter;
import com.github.wcquan.v2exclient.view.system.contract.SettingContract;

import java.io.File;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by WCQUAN on 2017-06-05.
 */

public class SettingActivity  extends BaseActivity<SettingPresenter> implements SettingContract.View{

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.cb_setting_cache)
    SwitchCompat cbSettingCache;
    @BindView(R.id.cb_setting_image)
    SwitchCompat cbSettingImage;
    @BindView(R.id.cb_setting_night)
    SwitchCompat cbSettingNight;
    @BindView(R.id.tv_setting_clear)
    TextView tvSettingClear;
    @BindView(R.id.ll_setting_about)
    LinearLayout llSettingAbout;
    @BindView(R.id.tv_setting_version_name)
    AppCompatTextView tvVersion;

    private File cacheFile;
    private boolean isNull = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        isNull = savedInstanceState == null;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolBar, R.string.title_setting);
        cacheFile = new File(Constants.PATH_CACHE);
        tvSettingClear.setText(ACache.getCacheSize(cacheFile));
        cbSettingCache.setChecked(mPresenter.getAutoCacheState());
        cbSettingImage.setChecked(mPresenter.getNoImageState());
        cbSettingNight.setChecked(mPresenter.getNightModeState());

        tvVersion.setText(String.format("当前版本号 v%s", Constants.VSERION_NAME));
    }

    @OnCheckedChanged(R.id.cb_setting_cache)
    void onAutoCacheChanged(boolean checked) {
        mPresenter.setAutoCacheState(checked);
    }

    @OnCheckedChanged(R.id.cb_setting_image)
    void onNoImageChanged(boolean checked) {
        mPresenter.setNoImageState(checked);
    }

    @OnCheckedChanged(R.id.cb_setting_night)
    void onNightModeChanged(boolean checked) {
        if (isNull) {   //防止夜间模式MainActivity执行reCreate后重复调用
            mPresenter.setNightModeState(checked);
            NightModeEvent event = new NightModeEvent();
            event.setNightMode(checked);
            RxBus.getDefault().post(event);
        }
    }

    @OnClick(R.id.ll_setting_clear)
    void doClear() {
        ACache.deleteDir(cacheFile);
        tvSettingClear.setText(ACache.getCacheSize(cacheFile));
        showSuccessMsg("缓存清理成功！");
    }

    @Override
    public void useNightMode(boolean isNight) {

    }


}
