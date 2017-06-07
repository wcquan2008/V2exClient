package com.github.wcquan.v2exclient.presenter;

import java.util.List;

import javax.inject.Inject;

import com.github.wcquan.library.base.RxPresenter;
import com.github.wcquan.library.model.DataManager;
import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.library.util.RxUtil;
import com.github.wcquan.library.widget.CommonSubscriber;
import com.github.wcquan.v2exclient.view.main.contract.TopicListContract;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class TopicListPresenter extends RxPresenter<TopicListContract.View> implements TopicListContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public TopicListPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getContent(String type) {
        addSubscribe(mDataManager.fetchTopicList(type)
                .compose(RxUtil.<List<TopicBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<TopicBean>>(mView) {
                    @Override
                    public void onNext(List<TopicBean> repliesListBeen) {
                        mView.showContent(repliesListBeen);
                    }
                })
        );
    }
}

