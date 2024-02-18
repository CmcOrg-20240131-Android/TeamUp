package com.cmcorg20230301.teamup.model.dto;

public class SignInNameNotBlankDTO extends UserSignBaseDTO {

    /**
     * 登录名
     */
    private String signInName;

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

}
