package com.github.wcquan.library.http;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import com.github.wcquan.library.http.api.V2exApi;
import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.model.bean.TopicBean;


/**
 * Created by WCQUAN on 2017-06-01.
 */
public class RetrofitHelper implements IHttpHelper {

    private V2exApi mV2exApiService;

    @Inject
    public RetrofitHelper(V2exApi v2exApiService) {
        this.mV2exApiService = v2exApiService;
    }

    @Override
    public Flowable<List<TopicBean>> fetchTopicList(String name) {
        return mV2exApiService.getTopicList(name);
    }

    @Override
    public Flowable<List<TopicBean>> fetchTopicInfo(long id) {
        return mV2exApiService.getTopicInfo(id);
    }

    @Override
    public Flowable<List<ReplyBean>> fetchRepliesList(long id){
        return mV2exApiService.getRepliesList(id);
    }
}
