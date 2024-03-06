package com.cmcorg20230301.teamup.model.dto;

public class SysImSessionApplyPrivateChatRejectDTO extends NotNullId {

    /**
     * 拒绝理由
     */
    private String rejectReason;

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
