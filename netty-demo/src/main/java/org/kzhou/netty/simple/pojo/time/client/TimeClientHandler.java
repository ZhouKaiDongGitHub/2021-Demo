package org.kzhou.netty.simple.pojo.time.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.kzhou.netty.simple.pojo.time.UnixTime;

import java.util.Date;

/**
 * @Description: 时间客户端处理类
 * @author: Admin
 * @date: 2021年05月07日 14:49
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        UnixTime unixTime = (UnixTime) msg;
        System.out.println("当前时间为:"+unixTime);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
