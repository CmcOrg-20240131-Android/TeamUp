package com.cmcorg20230301.teamup.util.websocket;

import java.net.URI;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

/**
 * webSocket帮助类
 */
public class WebSocketHelper {

    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        WebSocketClientHandler webSocketClientHandler = new WebSocketClientHandler();

        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) {

                ChannelPipeline pipeline = socketChannel.pipeline();

                pipeline.addLast(new HttpClientCodec());

                pipeline.addLast(new HttpObjectAggregator(8192));

                pipeline.addLast(webSocketClientHandler);

            }

        });

        // webSocket连接的地址，/hello是因为在服务端的websockethandler设置的
        URI uri = new URI("ws://localhost:8899/hello");

        // 客户端与服务端连接的通道
        Channel channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();

        HttpHeaders httpHeaders = new DefaultHttpHeaders();

        // 进行握手
        WebSocketClientHandshaker handshaker =
            WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, (String)null, true, httpHeaders);

        webSocketClientHandler.setHandshaker(handshaker);

        handshaker.handshake(channel);

        // 阻塞等待是否握手成功
        webSocketClientHandler.getHandshakeFuture().sync();

        System.out.println("握手成功");

        // 给服务端发送的内容，如果客户端与服务端连接成功后，可以多次掉用这个方法发送消息
        sengMessage(channel);

    }

    public static void sengMessage(Channel channel) {

        // 发送的内容，是一个文本格式的内容
        String putMessage = "你好，我是客户端";

        TextWebSocketFrame frame = new TextWebSocketFrame(putMessage);

        channel.writeAndFlush(frame).addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture channelFuture) throws Exception {

                if (channelFuture.isSuccess()) {

                    System.out.println("消息发送成功，发送的消息是：" + putMessage);

                } else {

                    System.out.println("消息发送失败 " + channelFuture.cause().getMessage());

                }

            }

        });
    }

}
