package com.cmcorg20230301.teamup.model.dto;

import java.util.Set;

public class MyTenantPageDTO extends MyPageDTO {

    /**
     * 租户 idSet
     */
    private Set<Long> tenantIdSet;

    public Set<Long> getTenantIdSet() {
        return tenantIdSet;
    }

    public void setTenantIdSet(Set<Long> tenantIdSet) {
        this.tenantIdSet = tenantIdSet;
    }

}
