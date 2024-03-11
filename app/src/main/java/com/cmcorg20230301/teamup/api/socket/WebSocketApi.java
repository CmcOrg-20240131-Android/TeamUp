package com.cmcorg20230301.teamup.api.socket;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.util.websocket.WebSocketHelper;

/**
 * api：webSocket相关
 */
public class WebSocketApi {

    // 心跳检测
    public static String NETTY_WEB_SOCKET_HEART_BEAT = "/netty/webSocket/heartBeat";

    // 关闭前端支付弹窗
    public static String SYS_PAY_CLOSE_MODAL = "/sys/pay/closeModal";

    // 即时通讯收到消息
    public static String SYS_IM_SESSION_CONTENT_SEND = "/sys/im/session/content/send";

    // 即时通讯，加入新用户
    public static String SYS_IM_SESSION_REF_USER_JOIN_USER_ID_SET = "/sys/im/session/refUser/join/userIdSet";

    // 开关控制台
    public static String SYS_SOCKET_REF_USER_CHANGE_CONSOLE_FLAG_BY_ID_SET =
        "/sys/socketRefUser/changeConsoleFlagByIdSet";

    /**
     * 心跳检测，请求
     */
    public static void heartBeatRequest(@Nullable okhttp3.WebSocket webSocket) {

        WebSocketHelper.send(webSocket, NETTY_WEB_SOCKET_HEART_BEAT);

    }

}
