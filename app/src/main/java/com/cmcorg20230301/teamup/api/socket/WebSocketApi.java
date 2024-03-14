package com.cmcorg20230301.teamup.api.socket;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.dto.NotNullId;
import com.cmcorg20230301.teamup.model.dto.SysImSessionContentSendTextListDTO;
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
    public static boolean heartBeatRequest(@Nullable okhttp3.WebSocket webSocket) {

        return WebSocketHelper.send(webSocket,
            WebSocketMessageDTO.okData(WebSocketUriEnum.NETTY_WEB_SOCKET_HEART_BEAT, null));

    }

    /**
     * 即时通讯-会话-内容：发送内容-文字-用户自我
     */
    public static boolean imSessionContentSendTextUserSelf(SysImSessionContentSendTextListDTO data) {

        return WebSocketHelper.send(null,
            WebSocketMessageDTO.okData(WebSocketUriEnum.SYS_IM_SESSION_CONTENT_WEB_SOCKET_SEND_TEXT_USER_SELF, data));

    }

    /**
     * 即时通讯-会话-用户：更新-最后一次打开会话的时间戳-用户自我
     */
    public static boolean imSessionContentRefUserUpdateLastOpenTsUserSelf(NotNullId data) {

        return WebSocketHelper.send(null, WebSocketMessageDTO
            .okData(WebSocketUriEnum.SYS_IM_SESSION_REF_USER_WEB_SOCKET_UPDATE_LAST_OPEN_TS_USER_SELF, data));

    }

}
