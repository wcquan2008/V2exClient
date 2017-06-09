package com.github.wcquan.v2exclient.presenter;

import java.util.List;

import javax.inject.Inject;

import com.github.wcquan.library.base.RxPresenter;
import com.github.wcquan.library.model.http.api.V2exService;
import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.library.util.RxUtil;
import com.github.wcquan.library.widget.CommonSubscriber;
import com.github.wcquan.v2exclient.view.main.contract.TopicListContract;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class TopicListPresenter extends RxPresenter<TopicListContract.View> implements TopicListContract.Presenter{

    private V2exService mV2exService;

    @Inject
    public TopicListPresenter(V2exService v2exService) {
        this.mV2exService = v2exService;
    }

    @Override
    public void getContent(String type) {
        addSubscribe(mV2exService.getTopicList(type)
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

