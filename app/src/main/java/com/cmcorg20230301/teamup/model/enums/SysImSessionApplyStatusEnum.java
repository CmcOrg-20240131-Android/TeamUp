package com.cmcorg20230301.teamup.model.enums;

import com.cmcorg20230301.teamup.model.configuration.ISysImSessionContentType;

/**
 * 会话申请状态，枚举类
 */
public enum SysImSessionApplyStatusEnum implements ISysImSessionContentType {

    APPLYING(101), // 申请中

    PASSED(201), // 已通过

    REJECTED(301), // 已拒绝

    BLOCKED(401), // 已被拉黑

    ;

    private final int code; // 类型编码

    SysImSessionApplyStatusEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

}
