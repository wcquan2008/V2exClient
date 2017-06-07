package com.github.wcquan.library.http;

import java.util.List;

import io.reactivex.Flowable;

import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.model.bean.TopicBean;


/**
 * Created by WCQUAN on 2017-06-01.
 */

public interface IHttpHelper {

    Flowable<List<TopicBean>> fetchTopicList(String name);

    Flowable<List<TopicBean>> fetchTopicInfo(long id);

    Flowable<List<ReplyBean>> fetchRepliesList(long id);
}
