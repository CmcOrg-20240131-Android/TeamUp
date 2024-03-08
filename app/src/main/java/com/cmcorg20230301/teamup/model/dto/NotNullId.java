package com.cmcorg20230301.teamup.model.dto;

public class NotNullId {

    public NotNullId() {}

    public NotNullId(Long id) {
        this.id = id;
    }

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
