package com.cmcorg20230301.teamup.model.dto;

/**
 * 分页参数，查询所有：pageSize = -1，默认：current = 1，pageSize = 10
 */
public class MyPageDTO {

    /**
     * 第几页
     */
    private long current = 1;

    /**
     * 每页显示条数
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private MyOrderDTO order;

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public MyOrderDTO getOrder() {
        return order;
    }

    public void setOrder(MyOrderDTO order) {
        this.order = order;
    }
}
