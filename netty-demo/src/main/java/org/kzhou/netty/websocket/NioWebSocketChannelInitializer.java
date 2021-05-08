package org.kzhou.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.apache.log4j.Logger;

/**
 * @Description: 管道初始化类
 * @author: Admin
 * @date: 2021年05月07日 10:19
 */
public class NioWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final Logger logger= Logger.getLogger(NioWebSocketChannelInitializer.class);

    public NioWebSocketChannelInitializer(){
        logger.info("NioWebSocketChannelInitializer 构造方法");
    }

    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("logging",new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
        ch.pipeline().addLast("http-codec",new HttpServerCodec());//设置解码器
        ch.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
        ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());//用于大数据的分区传输
        ch.pipeline().addLast("handler",new NioWebSocketHandler());//自定义的业务handler
    }
}
