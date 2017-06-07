package com.github.wcquan.library.http.api;

import java.util.List;

import io.reactivex.Flowable;

import com.github.wcquan.library.model.bean.MemberBean;
import com.github.wcquan.library.model.bean.NodeBean;
import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.model.bean.TopicBean;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by WCQUAN on 2017-06-01.
 */

public interface V2exApi {

    /**
     * 获取作者信息
     * @return
     */
    @GET("/api/members/show.json")
    Flowable<MemberBean> getMember(@Query(value = "username") String userName);


    /**
     * 获取节点
     * @return
     */
    @GET("/api/nodes/show.json")
    Flowable<NodeBean> getNode(@Query(value = "name") String name);

    /**
     * 获取最热主题列表
     * @return
     */
    @GET("/api/topics/hot.json")
    Flowable<List<TopicBean>> hot();

    /**
     * 获取最新主题列表
     * @return
     */
    @GET("/api/topics/latest.json")
    Flowable<List<TopicBean>> latest();

    /**
     * 获取主题列表
     * @return
     */
    @GET("/api/topics/show.json")
    Flowable<List<TopicBean>> getTopicList(@Query("node_name") String name);

    /**
     * 获取当前作者下面的主题列表
     * @return
     */
    @GET("/api/topics/show.json")
    Flowable<List<TopicBean>> getTopicListByUser(@Query(value = "username") String userName);

    /**
     * 获取主题信息
     * @return
     */
    @GET("/api/topics/show.json")
    Flowable<List<TopicBean>> getTopicInfo(@Query("id") long id);

    /**
     * 获取主题回复
     * @return
     */
    @GET("/api/replies/show.json")
    Flowable<List<ReplyBean>> getRepliesList(@Query("topic_id") long id);
}
