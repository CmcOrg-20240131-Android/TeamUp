package com.cmcorg20230301.teamup.model.entity;

import com.cmcorg20230301.teamup.model.configuration.ISysImSessionContentType;

/**
 * 子表：会话内容，主表：会话
 */
public class SysImSessionContentDO extends BaseEntity {

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 会话主键 id
     */
    private Long sessionId;

    /**
     * 会话内容
     */
    private String content;

    /**
     * 是否显示在：用户会话列表中
     */
    private Boolean showFlag;

    /**
     * 内容类型
     * {@link ISysImSessionContentType}
     */
    private Integer type;

    /**
     * 创建时间的时间戳
     */
    private Long createTs;

}