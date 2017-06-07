package com.github.wcquan.v2exclient.view.main.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.v2exclient.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.github.wcquan.library.app.Constants;
import com.github.wcquan.library.widget.CommonItemDecoration;
import com.github.wcquan.v2exclient.base.RootFragment;
import com.github.wcquan.v2exclient.presenter.TopicListPresenter;
import com.github.wcquan.v2exclient.view.main.adapter.TopicListAdapter;
import com.github.wcquan.v2exclient.view.main.contract.TopicListContract;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class TopicListFragment extends RootFragment<TopicListPresenter> implements TopicListContract.View{

    @BindView(R.id.view_main)
    RecyclerView rvContent;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private TopicListAdapter mAdapter;

    private String mType;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.view_common_list;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mType = getArguments().getString(Constants.IT_V2EX_TYPE);
        mAdapter = new TopicListAdapter(mContext, new ArrayList<TopicBean>());
        CommonItemDecoration mDecoration = new CommonItemDecoration(1, CommonItemDecoration.UNIT_DP);
        rvContent.setLayoutManager(new LinearLayoutManager(mContext));
        rvContent.setAdapter(mAdapter);
        rvContent.addItemDecoration(mDecoration);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getContent(mType);
            }
        });
        stateLoading();
        mPresenter.getContent(mType);
    }

    @Override
    public void stateError() {
        super.stateError();
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showContent(List<TopicBean> mList) {
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        stateMain();
        mAdapter.updateData(mList);
    }
}

