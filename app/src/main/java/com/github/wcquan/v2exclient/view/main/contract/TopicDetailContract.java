package com.github.wcquan.v2exclient.view.main.contract;

import java.util.List;

import com.github.wcquan.library.base.BasePresenter;
import com.github.wcquan.library.base.BaseView;
import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.model.bean.TopicBean;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public interface TopicDetailContract {
    interface View extends BaseView {

        void showContent(List<ReplyBean> mList);

        void showTopInfo(TopicBean mTopInfo);
    }

    interface Presenter extends BasePresenter<View> {

        void getContent(long topic_id);

        void getTopInfo(long topic_id);
    }
}
