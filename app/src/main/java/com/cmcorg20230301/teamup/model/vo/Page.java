package com.cmcorg20230301.teamup.model.vo;

import java.util.List;

public class Page<T> {

    /**
     * 总数
     */
    private Long total;

    /**
     * 每页显示条数，默认 10
     */
    private Long size;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 数据
     */
    private List<T> records;

}
