package org.kzhou.netty.chat.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;

/**
 * @Description: IM聊天室处理类
 * @author: Admin
 * @date: 2021年05月08日 11:00
 */
public class IMChannelHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger= Logger.getLogger(IMChannelHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelSupervise.addChannel(ctx.channel());
        logger.info(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelSupervise.addChannel(ctx.channel());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error( "IMChannelHandler处理发生异常" + cause.getMessage());
        ctx.close();
    }
}
