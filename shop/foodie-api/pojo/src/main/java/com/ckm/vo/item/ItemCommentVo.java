package com.ckm.vo.item;

import java.util.Date;

public class ItemCommentVo {
    private Integer commentLevel;
    private String content;
    private String itemsSpec;
    private Date createdTime;
    private String userFace;
    private String nickname;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getItemsSpec() {
        return itemsSpec;
    }

    public void setItemsSpec(String itemsSpec) {
        this.itemsSpec = itemsSpec;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
