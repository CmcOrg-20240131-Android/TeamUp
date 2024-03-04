package com.cmcorg20230301.teamup.model.entity;

import com.cmcorg20230301.teamup.model.enums.SysImSessionApplyStatusEnum;

/**
 * 主表：会话申请
 */
public class SysImSessionApplyDO extends BaseEntityNoIdSuper {

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 用户主键 id
     */
    private Long userId;

    /**
     * 会话主键 id
     */
    private Long sessionId;

    /**
     * 会话是私聊时，申请目标用户的主键 id，其他类型时，该值为：-1
     */
    private Long privateChatApplyTargetUserId;

    /**
     * 是否启用
     */
    private Boolean enableFlag;

    /**
     * 是否逻辑删除
     */
    private Boolean delFlag;

    /**
     * 申请加入会话的备注
     */
    private String remark;

    /**
     * {@link ISysImSessionType}
     */
    /**
     * 冗余字段，会话类型：101 私聊 201 群聊 301 客服
     */
    private Integer sessionType;

    /**
     * 状态：101 申请中 201 已通过 301 已拒绝
     */
    private SysImSessionApplyStatusEnum status;

    /**
     * 是否显示在申请列表
     */
    private Boolean showFlag;

    /**
     * 拒绝理由
     */
    private String rejectReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getPrivateChatApplyTargetUserId() {
        return privateChatApplyTargetUserId;
    }

    public void setPrivateChatApplyTargetUserId(Long privateChatApplyTargetUserId) {
        this.privateChatApplyTargetUserId = privateChatApplyTargetUserId;
    }

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSessionType() {
        return sessionType;
    }

    public void setSessionType(Integer sessionType) {
        this.sessionType = sessionType;
    }

    public SysImSessionApplyStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SysImSessionApplyStatusEnum status) {
        this.status = status;
    }

    public Boolean getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Boolean showFlag) {
        this.showFlag = showFlag;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}