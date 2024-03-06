package com.cmcorg20230301.teamup.model.dto;

/**
 * 滚动列表查询
 */
public class ScrollListDTO {

    /**
     * 主键 id，如果为 null，则根据 backwardFlag，来查询最大 id或者最小 id，注意：不会查询该 id的数据
     */
    private Long id;

    /**
     * 本次查询的长度，默认：20
     */
    private Integer pageSize;

    /**
     * 是否向后查询，默认：false 根据 id，往前查询 true 根据 id，往后查询
     */
    private Boolean backwardFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getBackwardFlag() {
        return backwardFlag;
    }

    public void setBackwardFlag(Boolean backwardFlag) {
        this.backwardFlag = backwardFlag;
    }
}
