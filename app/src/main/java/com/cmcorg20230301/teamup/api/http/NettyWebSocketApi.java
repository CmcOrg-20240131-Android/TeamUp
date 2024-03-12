package com.cmcorg20230301.teamup.api.http;

import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.model.dto.NotNullIdAndIntegerValue;
import com.cmcorg20230301.teamup.model.interfaces.IHttpHandle;
import com.cmcorg20230301.teamup.util.MyHttpUtil;

import cn.hutool.core.lang.TypeReference;

/**
 * 基础-netty-webSocket
 */
public class NettyWebSocketApi {

    /**
     * 获取：所有 webSocket连接地址，格式：scheme://ip:port/path?code=xxx
     */
    public static void getAllWebSocketUrl(@Nullable IHttpHandle<Set<String>> iHttpHandle) {

        MyHttpUtil.postHiddenError("/netty/webSocket/getAllWebSocketUrl", null, iHttpHandle,
            new TypeReference<Set<String>>() {});

    }

    /**
     * 通过主键 id，获取：webSocket连接地址，格式：scheme://ip:port/path?code=xxx
     */
    public static void getWebSocketUrlById(NotNullIdAndIntegerValue dto, @Nullable IHttpHandle<String> iHttpHandle) {

        MyHttpUtil.post("/netty/webSocket/getWebSocketUrlById", dto, iHttpHandle, new TypeReference<String>() {});

    }

}
