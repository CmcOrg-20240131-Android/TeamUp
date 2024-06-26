package com.cmcorg20230301.teamup.model.entity;

import com.cmcorg20230301.teamup.model.configuration.ISysImSessionContentType;

/**
 * 子表：会话内容，主表：会话
 */
public class SysImSessionContentDO extends BaseEntity {

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 会话主键 id
     */
    private Long sessionId;

    /**
     * 会话内容
     */
    private String content;

    /**
     * 是否显示在：用户会话列表中
     */
    private Boolean showFlag;

    /**
     * 内容类型 {@link ISysImSessionContentType}
     */
    private Integer type;

    /**
     * 创建时间的时间戳
     */
    private Long createTs;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Boolean showFlag) {
        this.showFlag = showFlag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Long createTs) {
        this.createTs = createTs;
    }
}