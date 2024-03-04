package com.cmcorg20230301.teamup.model.dto;

import java.util.Set;

public class NotEmptyIdSet {

    /**
     * 主键 idSet
     */
    private Set<Long> idSet;

    public Set<Long> getIdSet() {
        return idSet;
    }

    public void setIdSet(Set<Long> idSet) {
        this.idSet = idSet;
    }
}
