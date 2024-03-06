package com.cmcorg20230301.teamup.model.dto;

public class SysImSessionContentListDTO extends ScrollListDTO {

    /**
     * 会话主键 id
     */
    private Long sessionId;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
