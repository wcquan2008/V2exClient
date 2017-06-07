package com.github.wcquan.library.model.bean;

import com.google.gson.annotations.SerializedName;


/**
 * Created by WCQUAN on 2017-06-01.
 */

public class ReplyBean {

    private long id;
    private String thanks;
    @SerializedName("content_rendered")
    private String content;
    private MemberBean member;
    private long created;
    @SerializedName("last_modified")
    private long lastModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
