package com.github.wcquan.library.model;


import java.util.List;
import io.reactivex.Flowable;
import com.github.wcquan.library.db.IDBHelper;
import com.github.wcquan.library.http.IHttpHelper;
import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.library.model.pref.IPreferencesHelper;


/**
 * Created by WCQUAN on 2017-06-01.
 */

public class DataManager implements IHttpHelper, IDBHelper, IPreferencesHelper {

    IHttpHelper mIHttpHelper;
    IDBHelper mIDBHelper;
    IPreferencesHelper mPreferencesHelper;

    public DataManager(IHttpHelper IHttpHelper, IDBHelper IDBHelper, IPreferencesHelper preferencesHelper) {
        mIHttpHelper = IHttpHelper;
        mIDBHelper = IDBHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }

    @Override
    public boolean getAutoCacheState() {
        return mPreferencesHelper.getAutoCacheState();
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mPreferencesHelper.setAutoCacheState(state);
    }


    @Override
    public Flowable<List<TopicBean>> fetchTopicList(String name) {
        return mIHttpHelper.fetchTopicList(name);
    }

    @Override
    public Flowable<List<TopicBean>> fetchTopicInfo(long id) {
        return mIHttpHelper.fetchTopicInfo(id);
    }

    @Override
    public Flowable<List<ReplyBean>> fetchRepliesList(long id) {
        return mIHttpHelper.fetchRepliesList(id);
    }
}
