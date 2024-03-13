package com.cmcorg20230301.teamup.model.dto;

public class SysImSessionContentSendTextDTO {

    /**
     * 发送的内容
     */
    private String content;

    /**
     * 创建时间的时间戳
     */
    private Long createTs;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Long createTs) {
        this.createTs = createTs;
    }
}
