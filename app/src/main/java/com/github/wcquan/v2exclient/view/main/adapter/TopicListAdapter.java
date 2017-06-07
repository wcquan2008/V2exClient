package com.github.wcquan.v2exclient.view.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.wcquan.library.model.bean.TopicBean;
import com.github.wcquan.library.util.DateUtil;
import com.github.wcquan.v2exclient.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.wcquan.library.app.Constants;
import com.github.wcquan.library.component.ImageLoader;
import com.github.wcquan.library.widget.SquareImageView;
import com.github.wcquan.v2exclient.view.main.activity.TopicDetailActivity;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<TopicBean> mList;

    public TopicListAdapter(Context context, List<TopicBean> mList) {
        this.mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TopicBean bean = mList.get(position);
        ImageLoader.load(mContext, bean.getMember().getAvatarNormal(), holder.ivTopicFace);
        holder.tvTopicName.setText(bean.getMember().getUsername());
        holder.tvTopicTips.setText(DateUtil.formatTime2String(bean.getLastModified()));
        holder.tvTopicComment.setText(String.valueOf(bean.getReplies()));
        holder.tvTopicNode.setText(bean.getNode().getName());
        holder.tvTopicTitle.setText(bean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, TopicDetailActivity.class);
                intent.putExtra(Constants.IT_V2EX_TOPIC_ID, mList.get(holder.getAdapterPosition()).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(List<TopicBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_topic_face)
        SquareImageView ivTopicFace;
        @BindView(R.id.tv_topic_name)
        TextView tvTopicName;
        @BindView(R.id.tv_topic_tips)
        TextView tvTopicTips;
        @BindView(R.id.tv_topic_comment)
        TextView tvTopicComment;
        @BindView(R.id.tv_topic_node)
        TextView tvTopicNode;
        @BindView(R.id.tv_topic_title)
        TextView tvTopicTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

