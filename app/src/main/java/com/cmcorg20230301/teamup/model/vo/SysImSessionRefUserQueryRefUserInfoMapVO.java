package com.cmcorg20230301.teamup.model.vo;

public class SysImSessionRefUserQueryRefUserInfoMapVO {

    /**
     * 我在会话的昵称
     */
    private String sessionNickname;

    /**
     * 我在会话的头像地址
     */
    private String sessionAvatarUrl;

    public String getSessionNickname() {
        return sessionNickname;
    }

    public void setSessionNickname(String sessionNickname) {
        this.sessionNickname = sessionNickname;
    }

    public String getSessionAvatarUrl() {
        return sessionAvatarUrl;
    }

    public void setSessionAvatarUrl(String sessionAvatarUrl) {
        this.sessionAvatarUrl = sessionAvatarUrl;
    }
}
