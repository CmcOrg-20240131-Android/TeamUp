package com.cmcorg20230301.teamup.model.dto;

public class SignSignInNameSignInPasswordDTO extends SignInNameNotBlankDTO {

    /**
     * 前端加密之后的密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
