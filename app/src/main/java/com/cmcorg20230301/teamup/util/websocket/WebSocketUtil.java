package com.cmcorg20230301.teamup.util.websocket;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.cmcorg20230301.teamup.api.http.NettyWebSocket;
import com.cmcorg20230301.teamup.api.socket.WebSocketApi;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.dto.NotNullIdAndIntegerValue;
import com.cmcorg20230301.teamup.model.dto.WebSocketMessageDTO;
import com.cmcorg20230301.teamup.model.enums.AppDispatchKeyEnum;
import com.cmcorg20230301.teamup.model.enums.LocalStorageKeyEnum;
import com.cmcorg20230301.teamup.model.enums.SysSocketOnlineTypeEnum;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.model.vo.ApiResultVO;
import com.cmcorg20230301.teamup.util.MyLocalStorage;
import com.cmcorg20230301.teamup.util.common.CallBack;
import com.cmcorg20230301.teamup.util.common.LogUtil;
import com.cmcorg20230301.teamup.util.common.MyThreadUtil;
import com.cmcorg20230301.teamup.util.common.SysUtil;
import com.cmcorg20230301.teamup.util.common.TryUtil;

import androidx.annotation.NonNull;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * webSocket工具类
 */
public class WebSocketUtil {

    // 备注：开发环境的超时时间设置长一点
    public static long retryTime = SysUtil.devFlag() ? 5000 : 2000;

    public static WebSocket myWebSocket;

    public static ScheduledFuture<?> scheduledFuture;

    /**
     * 获取：webSocket的连接地址
     * 
     * @return null 表示暂无可连接的 webSocket
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

    /**
     * 关闭 webSocket
     */
    public static void closeWebSocket() {

        if (myWebSocket != null) {

            WebSocketHelper.close(myWebSocket);

        }

    }

    /**
     * 连接 webSocket
     */
    public static synchronized void connectWebSocket() {

        if (myWebSocket != null) {
            return;
        }

        TryUtil.tryCatch(() -> {

            String webSocketUrl = getWebSocketUrl();

            if (webSocketUrl == null) {

                MyThreadUtil.schedule(() -> {

                    myWebSocket = null; // 重置 webSocket对象，为了可以重新获取 webSocket连接地址

                    connectWebSocket();

                }, retryTime, TimeUnit.MILLISECONDS);

                return;

            }

            if (myWebSocket != null) {
                return;
            }

            myWebSocket = new OkHttpClient.Builder().build()
                .newWebSocket(new Request.Builder().url(webSocketUrl).build(), new WebSocketListener() {

                    @Override
                    public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {

                        LogUtil.debug("WebSocket 连接 >> {}", StrUtil.subBefore(webSocketUrl, "?", false));

                        BaseActivity.CURRENT_ACTIVITY.getAppDispatch(AppDispatchKeyEnum.SET_WEB_SOCKET_STATUS, true);

                        if (scheduledFuture != null) {

                            scheduledFuture.cancel(true);

                        }

                        scheduledFuture = MyThreadUtil.scheduleAtFixedRate(() -> {

                            // 心跳检测，请求
                            WebSocketApi.heartBeatRequest(webSocket);

                        }, 0, 25 * 1000, TimeUnit.MILLISECONDS);

                    }

                    @Override
                    public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {

                        WebSocketMessageDTO<String> webSocketMessageDTO =
                            JSONUtil.toBean(text, WebSocketMessageDTO.class);

                        LogUtil.debug("WebSocket 新消息：{}", text);

                        BaseActivity.CURRENT_ACTIVITY.getAppDispatch(AppDispatchKeyEnum.SET_WEB_SOCKET_MESSAGE,
                            webSocketMessageDTO);

                    }

                    @Override
                    public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {

                        LogUtil.debug("WebSocket 关闭");

                        BaseActivity.CURRENT_ACTIVITY.getAppDispatch(AppDispatchKeyEnum.SET_WEB_SOCKET_STATUS, false);

                        if (scheduledFuture != null) {

                            scheduledFuture.cancel(true);

                        }

                        MyThreadUtil.schedule(() -> {

                            myWebSocket = null; // 重置 webSocket对象，为了可以重新获取 webSocket连接地址

                            connectWebSocket();

                        }, retryTime, TimeUnit.MILLISECONDS);

                    }

                });

        });

    }

}
