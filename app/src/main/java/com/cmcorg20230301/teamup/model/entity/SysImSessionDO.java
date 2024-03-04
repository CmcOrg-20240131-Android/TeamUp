package com.cmcorg20230301.teamup.model.entity;

/**
 * 主表：会话
 */
public class SysImSessionDO extends BaseEntity {

    /**
     * 会话名
     */
    private String name;

    /**
     * 会话类型
     */
    private Integer type;

    /**
     * 归属者主键 id（群主），备注：如果为客服类型时，群主必须是用户
     */
    private Long belongId;

    /**
     * 最后一次接受到消息时的时间戳，默认为：-1，备注：该字段用于：排序
     */
    private Long lastReceiveContentTs;

    /**
     * 未读消息的总数量
     */
    private Integer unreadContentTotal;

    /**
     * 最后一条消息
     */
    private String lastContent;

    /**
     * 最后一条消息的内容类型
     */
    private Integer lastContentType;

    /**
     * 最后一条消息的创建时间戳
     */
    private Long lastContentCreateTs;

    /**
     * 私聊关联的另外一个用户主键 id
     */
    private Long privateChatRefUserId;

    /**
     * 我最后一次打开该会话的时间戳
     */
    private Long lastOpenTs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getBelongId() {
        return belongId;
    }

    public void setBelongId(Long belongId) {
        this.belongId = belongId;
    }

    public Long getLastReceiveContentTs() {
        return lastReceiveContentTs;
    }

    public void setLastReceiveContentTs(Long lastReceiveContentTs) {
        this.lastReceiveContentTs = lastReceiveContentTs;
    }

    public Integer getUnreadContentTotal() {
        return unreadContentTotal;
    }

    public void setUnreadContentTotal(Integer unreadContentTotal) {
        this.unreadContentTotal = unreadContentTotal;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public Integer getLastContentType() {
        return lastContentType;
    }

    public void setLastContentType(Integer lastContentType) {
        this.lastContentType = lastContentType;
    }

    public Long getLastContentCreateTs() {
        return lastContentCreateTs;
    }

    public void setLastContentCreateTs(Long lastContentCreateTs) {
        this.lastContentCreateTs = lastContentCreateTs;
    }

    public Long getPrivateChatRefUserId() {
        return privateChatRefUserId;
    }

    public void setPrivateChatRefUserId(Long privateChatRefUserId) {
        this.privateChatRefUserId = privateChatRefUserId;
    }

    public Long getLastOpenTs() {
        return lastOpenTs;
    }

    public void setLastOpenTs(Long lastOpenTs) {
        this.lastOpenTs = lastOpenTs;
    }

}