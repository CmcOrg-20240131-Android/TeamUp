package com.cmcorg20230301.teamup.model.enums;

import com.cmcorg20230301.teamup.model.interfaces.IWebSocketUri;

public enum WebSocketUriEnum implements IWebSocketUri {

    NETTY_WEB_SOCKET_HEART_BEAT("/netty/webSocket/heartBeat"), // 心跳检测

    SYS_PAY_CLOSE_MODAL("/sys/pay/closeModal"), // 关闭前端支付弹窗

    SYS_IM_SESSION_CONTENT_SEND("/sys/im/session/content/send"), // 即时通讯收到消息

    SYS_IM_SESSION_REF_USER_JOIN_USER_ID_SET("/sys/im/session/refUser/join/userIdSet"), // 即时通讯，加入新用户

    SYS_SOCKET_REF_USER_CHANGE_CONSOLE_FLAG_BY_ID_SET("/sys/socketRefUser/changeConsoleFlagByIdSet"), // 开关控制台

    SYS_IM_SESSION_CONTENT_WEB_SOCKET_SEND_TEXT_USER_SELF("/sys/im/session/content/webSocket/send/text/userSelf"), // 基础-即时通讯-会话-内容-webSocket-管理，用户自我-发送内容-文字

    SYS_IM_SESSION_REF_USER_WEB_SOCKET_UPDATE_LAST_OPEN_TS_USER_SELF(
        "/sys/im/session/refUser/webSocket/update/lastOpenTs/userSelf"), // 基础-即时通讯-会话-用户-webSocket-管理，更新-最后一次打开会话的时间戳-用户自我

    ;

    WebSocketUriEnum(String uri) {
        this.uri = uri;
    }

    private final String uri;

    @Override
    public String getUri() {
        return uri;
    }
}
