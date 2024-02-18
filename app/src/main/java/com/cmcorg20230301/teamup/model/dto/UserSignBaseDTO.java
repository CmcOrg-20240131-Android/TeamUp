package com.cmcorg20230301.teamup.model.dto;

public class UserSignBaseDTO {

    /**
     * 租户 id，可以为空，为空则表示：默认租户：0
     */
    private Long tenantId;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
