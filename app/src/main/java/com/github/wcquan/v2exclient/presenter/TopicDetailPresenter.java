package com.github.wcquan.v2exclient.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import com.github.wcquan.library.base.RxPresenter;
import com.github.wcquan.library.model.http.api.V2exService;
import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.library.util.RxUtil;
import com.github.wcquan.library.widget.CommonSubscriber;
import com.github.wcquan.v2exclient.view.main.contract.TopicDetailContract;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class TopicDetailPresenter extends RxPresenter<TopicDetailContract.View> implements TopicDetailContract.Presenter{

    private V2exService mV2exService;

    @Inject
    public TopicDetailPresenter(V2exService v2exService) {
        this.mV2exService = v2exService;
    }

    @Override
    public void getContent(long topic_id) {
        addSubscribe(mV2exService.getRepliesList(topic_id)
                .compose(RxUtil.<List<ReplyBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<ReplyBean>>(mView) {
                    @Override
                    public void onNext(List<ReplyBean> repliesListBeen) {
                        mView.showContent(repliesListBeen);
                    }
                })
        );
    }

    @Override
    public void getTopInfo(long topic_id) {
        addSubscribe(mV2exService.getTopicInfo(topic_id)
                .compose(RxUtil.<List<TopicBean>>rxSchedulerHelper())
                .filter(new Predicate<List<TopicBean>>() {
                    @Override
                    public boolean test(@NonNull List<TopicBean> nodeListBeen) throws Exception {
                        return nodeListBeen.size() > 0;
                    }
                })
                .map(new Function<List<TopicBean>, TopicBean>() {
                    @Override
                    public TopicBean apply(List<TopicBean> nodeListBeen) {
                        return nodeListBeen.get(0);
                    }
                })
                .subscribeWith(new CommonSubscriber<TopicBean>(mView) {
                    @Override
                    public void onNext(TopicBean nodeListBean) {
                        mView.showTopInfo(nodeListBean);
                    }
                })
        );
    }
}
