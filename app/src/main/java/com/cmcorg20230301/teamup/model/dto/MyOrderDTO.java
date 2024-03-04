package com.cmcorg20230301.teamup.model.dto;

public class MyOrderDTO {

    /**
     * 排序的字段名
     */
    private String name;

    /**
     * ascend（升序，默认） descend（降序）
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
