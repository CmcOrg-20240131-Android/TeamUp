package com.cmcorg20230301.teamup.model.enums;

/**
 * socket 在线状态
 */
public enum SysSocketOnlineTypeEnum {

    ONLINE(101), // 在线

    HIDDEN(201), // 隐身

    PING_TEST(100001), // ping测试，用于：获取延迟最低的 socket服务器

    ;

    private final int code;

    SysSocketOnlineTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
