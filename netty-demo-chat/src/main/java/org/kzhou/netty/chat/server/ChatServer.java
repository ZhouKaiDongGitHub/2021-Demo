package org.kzhou.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.kzhou.netty.chat.protocol.IMDecoder;
import org.kzhou.netty.chat.protocol.IMEncoder;
import org.kzhou.netty.chat.server.handler.HttpServerHandler;
import org.kzhou.netty.chat.server.handler.TerminalServerHandler;
import org.kzhou.netty.chat.server.handler.WebSocketServerHandler;

import java.util.HashMap;

/**
 * @Description: IM聊天室服务端
 * @author: Admin
 * @date: 2021年05月08日 10:53
 */
public class ChatServer {

    private int port  = 8080;

    private void run (){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline =  ch.pipeline();
                            //解析自定义协议
                            pipeline.addLast(new IMDecoder()); //Inbound
                            pipeline.addLast(new IMEncoder()); //Outbound

                            pipeline.addLast(new TerminalServerHandler()); // Inbound

                            //解析Http协议
                            pipeline.addLast(new HttpServerCodec()); // Inbound Outbound
                            pipeline.addLast(new HttpObjectAggregator(65536));//聚合器
                            pipeline.addLast(new ChunkedWriteHandler());//用于大数据的分区传输
                            pipeline.addLast(new HttpServerHandler());

                            //解析WebSocket协议
                            pipeline.addLast(new WebSocketServerProtocolHandler("/im")); // Inbound
                            pipeline.addLast(new WebSocketServerHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatServer().run();
    }
}
