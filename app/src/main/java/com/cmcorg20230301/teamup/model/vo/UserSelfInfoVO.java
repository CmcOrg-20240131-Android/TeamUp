package com.cmcorg20230301.teamup.model.vo;

import java.util.Date;

import com.cmcorg20230301.teamup.model.dto.UserSelfUpdateInfoDTO;

public class UserSelfInfoVO extends UserSelfUpdateInfoDTO {

    /**
     * 用户主键 id
     */
    private Long id;

    /**
     * 租户 id，可以为空，为空则表示：默认租户：0
     */
    private Long tenantId;

    /**
     * 邮箱，会脱敏
     */
    private String email;

    /**
     * 是否有密码，用于前端显示，修改密码/设置密码
     */
    private Boolean passwordFlag;

    /**
     * 登录名，会脱敏
     */
    private String signInName;

    /**
     * 手机号码，会脱敏
     */
    private String phone;

    /**
     * 微信 openId，会脱敏
     */
    private String wxOpenId;

    /**
     * 微信 appId，会脱敏
     */
    private String wxAppId;

    /**
     * 账号注册时间
     */
    private Date createTime;

    /**
     * 头像 fileId（文件主键 id）
     */
    private Long avatarFileId;

    /**
     * 是否设置了：统一登录：微信
     */
    private Boolean singleSignInWxFlag;

    /**
     * 是否设置了：统一登录：手机
     */
    private Boolean singleSignInPhoneFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPasswordFlag() {
        return passwordFlag;
    }

    public void setPasswordFlag(Boolean passwordFlag) {
        this.passwordFlag = passwordFlag;
    }

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAvatarFileId() {
        return avatarFileId;
    }

    public void setAvatarFileId(Long avatarFileId) {
        this.avatarFileId = avatarFileId;
    }

    public Boolean getSingleSignInWxFlag() {
        return singleSignInWxFlag;
    }

    public void setSingleSignInWxFlag(Boolean singleSignInWxFlag) {
        this.singleSignInWxFlag = singleSignInWxFlag;
    }

    public Boolean getSingleSignInPhoneFlag() {
        return singleSignInPhoneFlag;
    }

    public void setSingleSignInPhoneFlag(Boolean singleSignInPhoneFlag) {
        this.singleSignInPhoneFlag = singleSignInPhoneFlag;
    }
}
