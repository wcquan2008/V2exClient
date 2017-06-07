package com.github.wcquan.v2exclient.view.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.v2exclient.R;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.wcquan.library.component.ImageLoader;
import com.github.wcquan.library.model.bean.ReplyBean;
import com.github.wcquan.library.util.DateUtil;
import com.github.wcquan.library.widget.SquareImageView;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class TopicDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_HEADER = 0;
    private static final int VIEW_CELL = 1;

    private Context mContext;
    private LayoutInflater inflater;
    private List<ReplyBean> mList;
    private TopicBean mTopBean;

    public TopicDetailAdapter(Context context, List<ReplyBean> mList, TopicBean mTopBean) {
        this.mContext = context;
        this.mList = mList;
        this.mTopBean = mTopBean;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        } else {
            return VIEW_CELL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_HEADER:
                return new TopViewHolder(inflater.inflate(R.layout.item_topicdetail_header, parent, false));
            default:
            case VIEW_CELL:
                return new ViewHolder(inflater.inflate(R.layout.item_reply, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            TopViewHolder topHolder = ((TopViewHolder) holder);
            if (mTopBean == null) {
                return;
            }
            ImageLoader.load(mContext, mTopBean.getMember().getAvatarNormal(), topHolder.ivRepliesTopFace);
            topHolder.tvRepliesTopContent.setHtml(mTopBean.getContent(), new HtmlHttpImageGetter(topHolder.tvRepliesTopContent));
            topHolder.tvRepliesTopName.setText(mTopBean.getMember().getUsername());
            topHolder.tvRepliesTopTitle.setText(mTopBean.getTitle());
            topHolder.tvRepliesTopNum.setText(String.format("%s,   共%s条回复", DateUtil.formatTime2String(mTopBean.getCreated()), mTopBean.getReplies()));
        } else {
            ViewHolder contentHolder = ((ViewHolder) holder);
            ReplyBean bean = mList.get(position - 1);
            if (bean == null)
                return;
            ImageLoader.load(mContext, bean.getMember().getAvatarNormal(), contentHolder.ivRepliesFace);
            contentHolder.tvRepliesName.setText(bean.getMember().getUsername());
            contentHolder.tvRepliesTips.setText(String.format("%d楼 %s", position, DateUtil.formatTime2String(bean.getCreated())));
            contentHolder.tvRepliesContent.setHtml(bean.getContent(), new HtmlHttpImageGetter(contentHolder.tvRepliesContent));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_replies_top_title)
        TextView tvRepliesTopTitle;
        @BindView(R.id.iv_replies_top_face)
        SquareImageView ivRepliesTopFace;
        @BindView(R.id.tv_replies_top_name)
        TextView tvRepliesTopName;
        @BindView(R.id.tv_replies_top_num)
        TextView tvRepliesTopNum;
        @BindView(R.id.tv_replies_top_content)
        HtmlTextView tvRepliesTopContent;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_replies_face)
        SquareImageView ivRepliesFace;
        @BindView(R.id.tv_replies_name)
        TextView tvRepliesName;
        @BindView(R.id.tv_replies_tips)
        TextView tvRepliesTips;
        @BindView(R.id.tv_replies_content)
        HtmlTextView tvRepliesContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setTopData(TopicBean mTopBean) {
        this.mTopBean = mTopBean;
        notifyItemChanged(0);
    }

    public void setContentData(List<ReplyBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
}

