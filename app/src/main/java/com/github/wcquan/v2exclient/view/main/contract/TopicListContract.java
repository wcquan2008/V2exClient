package com.github.wcquan.v2exclient.view.main.contract;

import java.util.List;

import com.github.wcquan.library.base.BasePresenter;
import com.github.wcquan.library.base.BaseView;
import com.github.wcquan.library.model.bean.TopicBean;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public interface TopicListContract {
    interface View extends BaseView {

        void showContent(List<TopicBean> mList);

    }

    interface Presenter extends BasePresenter<View> {

        void getContent(String type);

    }
}
