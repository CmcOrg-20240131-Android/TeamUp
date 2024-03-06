package com.cmcorg20230301.teamup.model.enums;

/**
 * 会话申请状态，枚举类
 */
public enum SysImSessionApplyStatusEnum {

    APPLYING(101), // 申请中

    PASSED(201), // 已通过

    REJECTED(301), // 已拒绝

    BLOCKED(401), // 已被拉黑

    ;

    private final int code; // 类型编码

    SysImSessionApplyStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
