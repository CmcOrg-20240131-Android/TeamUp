package com.cmcorg20230301.teamup.util.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.api.http.NettyWebSocket;
import com.cmcorg20230301.teamup.api.socket.WebSocketApi;
import com.cmcorg20230301.teamup.model.dto.WebSocketMessageDTO;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.util.common.CallBack;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;

import androidx.annotation.NonNull;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * webSocket帮助类
 */
public class WebSocketHelper {

    /**
     * 处理：所有 webSocketUrl
     */
    public static Long handleAllWebSocketUrl(List<String> webSocketUrlList) throws Exception {

        if (CollUtil.isEmpty(webSocketUrlList)) {
            return null;
        }

        long checkMaxMs = 3000;

        CallBack<Long> minMsCallBack = new CallBack<>(-1L); // 最低延迟，单位：毫秒

        CallBack<Long> resWebSocketIdCallBack = new CallBack<>(null); // 返回值：webSocketId

        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(webSocketUrlList.size());

        for (String item : webSocketUrlList) {

            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

            OkHttpClient okHttpClient = httpBuilder.build();

            Request.Builder requestBuilder = new Request.Builder();

            requestBuilder.url(item);

            CallBack<Long> beginTsCallBack = new CallBack<>();

            okHttpClient.newWebSocket(requestBuilder.build(), new WebSocketListener() {

                @Override
                public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {

                    beginTsCallBack.setValue(System.currentTimeMillis());

                    // 心跳检测，请求
                    WebSocketApi.heartBeatRequest(webSocket);

                    MyThreadUtil.schedule(() -> {

                        close(webSocket); // 关闭：webSocket

                    }, checkMaxMs, TimeUnit.MILLISECONDS);

                }

                @Override
                public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {

                    WebSocketMessageDTO<String> webSocketMessageDTO = JSONUtil.toBean(text, WebSocketMessageDTO.class);

                    if (webSocketMessageDTO.getUri().equals(WebSocketApi.NETTY_WEB_SOCKET_HEART_BEAT)) {

                        Long dataLong = Convert.toLong(webSocketMessageDTO.getData());

                        if (dataLong != null) {

                            long ms = System.currentTimeMillis() - beginTsCallBack.getValue();

                            Long minMs = minMsCallBack.getValue();

                            if (minMs == -1 || ms < minMs) {

                                minMsCallBack.setValue(minMs);

                                resWebSocketIdCallBack.setValue(dataLong);

                            }

                        }

                        close(webSocket); // 关闭：webSocket

                    }

                }

                @Override
                public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t,
                    @androidx.annotation.Nullable Response response) {

                    countDownLatch.countDown();

                }

                @Override
                public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {

                    countDownLatch.countDown();

                }

            });

        }

        countDownLatch.await();

        return resWebSocketIdCallBack.getValue();

    }

    /**
     * 获取：延迟最低的 webSocketId
     */
    public static Long getWebSocketId() throws Exception {

        CallBack<Long> resWebSocketIdCallBack = new CallBack<>(null); // 返回值：webSocketId

        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(1);

        NettyWebSocket.getAllWebSocketUrl(new IHttpHandle<Set<String>>() {

            @Override
            public void success(ApiResultVO<Set<String>> apiResultVO) throws Exception {

                Long webSocketId = handleAllWebSocketUrl(new ArrayList<>(apiResultVO.getData()));

                resWebSocketIdCallBack.setValue(webSocketId);

            }

        });

        countDownLatch.await();

        return resWebSocketIdCallBack.getValue();

    }

    /**
     * 关闭
     */
    public static void close(WebSocket webSocket) {

        webSocket.close(1001, "客户端主动关闭连接");

    }

    /**
     * 发送
     */
    public static void send(@Nullable WebSocket webSocket, Object object) {

        if (webSocket == null) {

            webSocket = WebSocketUtil.webSocket;

        }

        if (webSocket == null) {

            return;

        }

        webSocket.send(JSONUtil.toJsonStr(object));

    }

}
