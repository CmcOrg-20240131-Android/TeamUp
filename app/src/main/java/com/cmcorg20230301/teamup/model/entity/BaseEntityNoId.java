package com.cmcorg20230301.teamup.model.entity;

/**
 * 实体类基类-没有主键 id
 */
public class BaseEntityNoId extends BaseEntityNoIdSuper {

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 是否启用
     */
    private Boolean enableFlag;

    /**
     * 是否逻辑删除
     */
    private Boolean delFlag;

    /**
     * 备注
     */
    private String remark;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
