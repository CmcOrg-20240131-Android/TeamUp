package com.cmcorg20230301.teamup.model.dto;

public class SignSignInNameSignUpDTO extends SignInNameNotBlankDTO {

    /**
     * 前端加密之后的密码
     */
    private String password;

    /**
     * 前端加密之后的原始密码
     */
    private String originPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        this.originPassword = originPassword;
    }

}
