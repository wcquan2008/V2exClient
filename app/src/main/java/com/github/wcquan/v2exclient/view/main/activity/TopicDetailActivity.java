package com.github.wcquan.v2exclient.view.main.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.v2exclient.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.github.wcquan.library.app.Constants;
import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.widget.CommonItemDecoration;
import com.github.wcquan.v2exclient.base.RootActivity;
import com.github.wcquan.v2exclient.presenter.TopicDetailPresenter;
import com.github.wcquan.v2exclient.view.main.adapter.TopicDetailAdapter;
import com.github.wcquan.v2exclient.view.main.contract.TopicDetailContract;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class TopicDetailActivity extends RootActivity<TopicDetailPresenter> implements TopicDetailContract.View {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_main)
    RecyclerView rvContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private TopicDetailAdapter mAdapter;
    private TopicBean mTopBean;
    private long topicId;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_topicdetail;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setToolBar(toolBar, "帖子详情");
        topicId = getIntent().getExtras().getLong(Constants.IT_V2EX_TOPIC_ID, 0);
        mAdapter = new TopicDetailAdapter(mContext, new ArrayList<ReplyBean>(), mTopBean);
        CommonItemDecoration mDecoration = new CommonItemDecoration(2, CommonItemDecoration.UNIT_PX);
        rvContent.addItemDecoration(mDecoration);
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getContent(topicId);
            }
        });
        stateLoading();
        mPresenter.getContent(topicId);
        if (mTopBean == null) {
            mPresenter.getTopInfo(topicId);
        }
    }


    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }


    @Override
    public void showContent(List<ReplyBean> mList) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mAdapter.setContentData(mList);
    }

    @Override
    public void showTopInfo(TopicBean mTopInfo) {
        mTopBean = mTopInfo;


        mAdapter.setTopData(mTopInfo);
    }

}

