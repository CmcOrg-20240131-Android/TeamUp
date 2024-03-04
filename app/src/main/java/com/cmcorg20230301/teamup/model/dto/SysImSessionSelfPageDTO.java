package com.cmcorg20230301.teamup.model.dto;

public class SysImSessionSelfPageDTO extends MyTenantPageDTO {

    /**
     * 私聊关联的另外一个用户主键 id
     */
    private Long privateChatRefUserId;

    public Long getPrivateChatRefUserId() {
        return privateChatRefUserId;
    }

    public void setPrivateChatRefUserId(Long privateChatRefUserId) {
        this.privateChatRefUserId = privateChatRefUserId;
    }
}
