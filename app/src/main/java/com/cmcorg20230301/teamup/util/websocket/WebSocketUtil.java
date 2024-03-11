package com.cmcorg20230301.teamup.util.websocket;

import java.util.concurrent.CountDownLatch;

import com.cmcorg20230301.teamup.api.http.NettyWebSocket;
import com.cmcorg20230301.teamup.model.dto.NotNullIdAndIntegerValue;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.enums.SysSocketOnlineTypeEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.common.CallBack;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import okhttp3.WebSocket;

/**
 * webSocket工具类
 */
public class WebSocketUtil {

    public static WebSocket webSocket;

    /**
     * 获取：webSocket的连接地址
     */
    public static String getWebSocketUrl() throws Exception {

        Long webSocketId = WebSocketHelper.getWebSocketId();

        if (webSocketId == null) {
            return null;
        }

        if (StrUtil.isBlank(MyLocalStorage.getItem(LocalStorageKeyEnum.JWT))) {
            return null;
        }

        NotNullIdAndIntegerValue notNullIdAndIntegerValue = new NotNullIdAndIntegerValue();

        notNullIdAndIntegerValue.setValue(SysSocketOnlineTypeEnum.ONLINE.getCode());
        notNullIdAndIntegerValue.setId(webSocketId);

        CallBack<String> webSocketUrlCallBack = new CallBack<>(null); // 返回值：webSocketId

        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(1);

        NettyWebSocket.getWebSocketUrlById(notNullIdAndIntegerValue, new IHttpHandle<String>() {

            @Override
            public void success(ApiResultVO<String> apiResultVO) {

                webSocketUrlCallBack.setValue(apiResultVO.getData());

            }

        });

        countDownLatch.await();

        return webSocketUrlCallBack.getValue();

    }

    public synchronized void connectWebSocket() throws Exception {

        if (webSocket != null) {
            return;
        }

        if (StrUtil.isBlank(MyLocalStorage.getItem(LocalStorageKeyEnum.JWT))) {
            return; // 如果没有 jwt，则不重连了，目的：防止一直连
        }

    }

}
