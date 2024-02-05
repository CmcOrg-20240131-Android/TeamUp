package com.cmcorg20230301.teamup.model.vo;

import lombok.Data;

/**
 * 统一响应实体类
 */
@Data
public class ApiResultVO<T> {

    /**
     * 响应代码，成功返回：200
     */
    private Integer code;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 服务器是否收到请求，只会返回 true
     */
    private Boolean successFlag;

    /**
     * 数据
     */
    private T data;

    /**
     * 服务名
     */
    private String service;

}
