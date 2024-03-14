package com.cmcorg20230301.teamup.model.dto;

import java.util.Set;

public class SysImSessionContentSendTextListDTO {

    /**
     * 会话主键 id
     */
    private Long sessionId;

    /**
     * 发送内容集合
     */
    private Set<SysImSessionContentSendTextDTO> contentSet;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Set<SysImSessionContentSendTextDTO> getContentSet() {
        return contentSet;
    }

    public void setContentSet(Set<SysImSessionContentSendTextDTO> contentSet) {
        this.contentSet = contentSet;
    }
}
