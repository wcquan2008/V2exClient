package com.github.wcquan.v2exclient.view.main.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.wcquan.library.adapter.BaseFragmentPagerAdapter;
import com.github.wcquan.library.app.Constants;
import com.github.wcquan.library.util.DisplayUtils;
import com.github.wcquan.v2exclient.R;
import com.github.wcquan.v2exclient.base.BaseActivity;
import com.github.wcquan.v2exclient.presenter.MainPresenter;
import com.github.wcquan.v2exclient.view.main.contract.MainContract;
import com.github.wcquan.v2exclient.view.main.fragment.TopicListFragment;
import com.github.wcquan.v2exclient.view.system.activity.SettingActivity;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{

    @BindView(R.id.fab_home_random)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.iv_home_banner)
    ImageView mIvHomeBanner;
    @BindView(R.id.vp_home_category)
    ViewPager mVpCategory;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.tl_home_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_home_category)
    DachshundTabLayout mDachshundTabLayout;
    @BindView(R.id.iv_home_setting)
    AppCompatImageView mIvSetting;

    public static String[] typeStr = {"Android", "Python", "Java ", "PHP", "Linux", "MySQL", "程序员", "云计算", "服务器"};
    public static String[] type = {"android", "python", "java ", "php", "linux", "mysql", "programmer", "cloud", "server"};

    List<TopicListFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mPresenter.setNightModeState(false);
        }
    }
    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        // 设置 Toolbar 高度为 80dp，适配状态栏
        ViewGroup.LayoutParams layoutParams = mToolbar.getLayoutParams();
        layoutParams.height = DisplayUtils.dp2px(80, this);
        mToolbar.setLayoutParams(layoutParams);


        setFabDynamicState();

        for (int i = 0; i < type.length; i++) {
            TopicListFragment fragment = new TopicListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.IT_V2EX_TYPE, type[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        BaseFragmentPagerAdapter infoPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(),
                fragments, typeStr);
        mVpCategory.setAdapter(infoPagerAdapter);
        mDachshundTabLayout.setupWithViewPager(mVpCategory);
        mVpCategory.setCurrentItem(0);
    }

    @OnClick(R.id.iv_home_setting)
    public void goSetting() {
        Intent intent = new Intent(mContext, SettingActivity.class);
        startActivity(intent);
    }

    private CollapsingToolbarLayoutState state; // CollapsingToolbarLayout 折叠状态
    private enum CollapsingToolbarLayoutState {
        EXPANDED, // 完全展开
        COLLAPSED, // 折叠
        INTERNEDIATE // 中间状态
    }
    private void setFabDynamicState() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED; // 修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        mFloatingActionButton.hide();
                        state = CollapsingToolbarLayoutState.COLLAPSED; // 修改状态标记为折叠
                        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
                        layoutParams.height = DisplayUtils.dp2px(240, MainActivity.this);
                        mAppBarLayout.setLayoutParams(layoutParams);
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                            mFloatingActionButton.show();
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE; // 修改状态标记为中间
                    }
                }
            }
        });
    }

    public void checkPermissions() {
        mPresenter.checkPermissions(new RxPermissions(this));
    }

    @OnClick(R.id.fab_home_random)
    public void displayAboutDialog() {
        final int paddingSizeDp = 5;
        final float scale = getResources().getDisplayMetrics().density;
        final int dpAsPixels = (int) (paddingSizeDp * scale + 0.5f);

        final TextView textView = new TextView(this);
        final SpannableString text = new SpannableString(getString(R.string.about_text));

        textView.setText(text);
        textView.setAutoLinkMask(RESULT_OK);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

        Linkify.addLinks(text, Linkify.ALL);
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_about)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .setView(textView)
                .show();
    }
}
