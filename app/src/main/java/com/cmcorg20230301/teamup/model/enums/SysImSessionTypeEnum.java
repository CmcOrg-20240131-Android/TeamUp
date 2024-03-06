package com.cmcorg20230301.teamup.model.enums;

import com.cmcorg20230301.teamup.model.configuration.ISysImSessionType;

/**
 * 会话类型，枚举类
 */
public enum SysImSessionTypeEnum implements ISysImSessionType {

    PRIVATE_CHAT(101), // 私聊

    GROUP_CHAT(201), // 群聊

    CUSTOMER(301), // 客服

    ;

    private final int code; // 类型编码

    SysImSessionTypeEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
