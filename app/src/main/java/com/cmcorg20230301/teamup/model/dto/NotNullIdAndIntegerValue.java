package com.cmcorg20230301.teamup.model.dto;

public class NotNullIdAndIntegerValue extends NotNullId {

    /**
     * 值
     */
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
