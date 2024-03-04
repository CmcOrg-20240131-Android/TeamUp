package com.cmcorg20230301.teamup.model.enums;

public enum LocalStorageKeyEnum {

    JWT, // jwt

    JWT_EXPIRE_TS, // jwt过期时间戳

    USER_SELF_MENU_LIST, // 用户菜单

    USER_SELF_AVATAR_URL, // 用户头像

    TENANT_ID, // 租户 id

    OTHER_APP_ID, // 第三方应用 id

    SYS_SIGN_CONFIGURATION_VO, // 登录注册相关的配置

    SYS_SIGN_CONFIGURATION_VO_SINGLE, // 登录注册相关的配置：统一登录

    SIGN_IN_TYPE, // 登录方式

    SIGN_IN_TYPE_SINGLE, // 登录方式：统一登录

    IM_SESSION_LIST, // 会话列表集合

}
