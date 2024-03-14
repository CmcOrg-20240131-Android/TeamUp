package com.cmcorg20230301.teamup.model.dto;

import java.util.Set;

public class NotNullIdAndNotEmptyLongSet extends NotNullId {

    public NotNullIdAndNotEmptyLongSet() {}

    public NotNullIdAndNotEmptyLongSet(Long id, Set<Long> valueSet) {

        super(id);
        this.valueSet = valueSet;

    }

    /**
     * 值 set
     */
    private Set<Long> valueSet;

    public Set<Long> getValueSet() {
        return valueSet;
    }

    public void setValueSet(Set<Long> valueSet) {
        this.valueSet = valueSet;
    }
}
