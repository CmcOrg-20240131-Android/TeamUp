package com.cmcorg20230301.teamup.api.socket;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.dto.WebSocketMessageDTO;
import com.cmcorg20230301.teamup.model.enums.WebSocketUriEnum;
import com.cmcorg20230301.teamup.util.websocket.WebSocketHelper;

/**
 * api：webSocket相关
 */
public class WebSocketApi {

    /**
     * 心跳检测，请求
     */
    public static void heartBeatRequest(@Nullable okhttp3.WebSocket webSocket) {

        WebSocketHelper.send(webSocket, WebSocketMessageDTO.okData(WebSocketUriEnum.NETTY_WEB_SOCKET_HEART_BEAT, null));

    }

}
