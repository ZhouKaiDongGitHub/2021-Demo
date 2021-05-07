package org.kzhou.netty.simple.discard;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @Description: 管道初始化类
 * @author: Admin
 * @date: 2021年05月07日 13:53
 */
public class DiscardServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new DiscardServerHandler());
    }
}
