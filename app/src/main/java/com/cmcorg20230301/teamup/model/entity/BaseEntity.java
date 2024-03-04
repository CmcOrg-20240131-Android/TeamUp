package com.cmcorg20230301.teamup.model.entity;

/**
 * 实体类基类
 */
public class BaseEntity extends BaseEntityNoId {

    /**
     * 主键 id
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
