package com.cmcorg20230301.teamup.model.enums;

import com.cmcorg20230301.teamup.model.configuration.ISysImSessionContentType;

/**
 * 会话内容类型，枚举类
 */
public enum SysImSessionContentTypeEnum implements ISysImSessionContentType {

    TEXT(101), // 文字

    IMAGE(201), // 图片

    ;

    private final int code; // 类型编码

    SysImSessionContentTypeEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
