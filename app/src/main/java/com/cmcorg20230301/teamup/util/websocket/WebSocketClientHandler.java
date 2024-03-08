package com.cmcorg20230301.teamup.util.websocket;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;

public class WebSocketClientHandler extends ChannelInboundHandlerAdapter {

    // 握手的状态信息
    private WebSocketClientHandshaker handshaker;

    // netty自带的异步处理
    private ChannelPromise handshakeFuture;

    public WebSocketClientHandshaker getHandshaker() {
        return handshaker;
    }

    public void setHandshaker(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }

    public ChannelPromise getHandshakeFuture() {
        return handshakeFuture;
    }

    public void setHandshakeFuture(ChannelPromise handshakeFuture) {
        this.handshakeFuture = handshakeFuture;
    }

}
