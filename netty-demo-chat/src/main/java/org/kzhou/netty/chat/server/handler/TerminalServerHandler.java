package org.kzhou.netty.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.kzhou.netty.chat.protocol.IMMessage;
import org.kzhou.netty.chat.server.processor.MsgProcessor;

public class TerminalServerHandler extends SimpleChannelInboundHandler<IMMessage> {

    private MsgProcessor msgProcessor = new MsgProcessor();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, IMMessage imMessage) throws Exception {
        msgProcessor.process(channelHandlerContext.channel(),imMessage);
    }
}
