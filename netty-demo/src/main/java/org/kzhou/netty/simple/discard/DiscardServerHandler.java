package org.kzhou.netty.simple.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.Logger;
import org.kzhou.netty.websocket.NioWebSocketHandler;

/**
 * @Description: 丢弃服务器处理类
 * @author: Admin
 * @date: 2021年05月07日 13:35
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger= Logger.getLogger(NioWebSocketHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // do nothing
            logger.info("discard channel handler do nothing!!");
            ByteBuf byteBuf = (ByteBuf)msg;
            while (byteBuf.isReadable()){
                logger.info("输出信息：");
                System.out.print((char) byteBuf.readByte());
                System.out.flush();
            }
        }finally {
            logger.info("引用计数器释放");
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        logger.error("消息处理程序处理异常");
        ctx.close();
    }
}
