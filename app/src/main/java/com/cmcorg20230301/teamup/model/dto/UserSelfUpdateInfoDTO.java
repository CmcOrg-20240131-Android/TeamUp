package com.cmcorg20230301.teamup.model.dto;

public class UserSelfUpdateInfoDTO {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 个人简介
     */
    private String bio;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
