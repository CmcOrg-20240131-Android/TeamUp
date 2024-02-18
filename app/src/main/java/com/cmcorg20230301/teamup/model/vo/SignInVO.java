package com.cmcorg20230301.teamup.model.vo;

public class SignInVO {

    /**
     * jwt
     */
    private String jwt;

    /**
     * jwt过期时间戳
     */
    private Long jwtExpireTs;

    /**
     * 租户主键 id
     */
    private Long tenantId;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getJwtExpireTs() {
        return jwtExpireTs;
    }

    public void setJwtExpireTs(Long jwtExpireTs) {
        this.jwtExpireTs = jwtExpireTs;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
