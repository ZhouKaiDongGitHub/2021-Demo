package org.kzhou.netty.chat.server.processor;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.kzhou.netty.chat.protocol.IMDecoder;
import org.kzhou.netty.chat.protocol.IMEncoder;
import org.kzhou.netty.chat.protocol.IMMessage;
import org.kzhou.netty.chat.protocol.IMProtocol;
import org.kzhou.netty.chat.server.ChannelSupervise;

/**
 * 消息处理器：主要处理控制台和UI上Websocket传来的消息
 */
public class MsgProcessor {

    private IMEncoder imEncoder = new IMEncoder();
    private IMDecoder imDecoder = new IMDecoder();

    public void process(Channel client, String msg){
        process(client, imDecoder.decode(msg));
    }

    /**
     * 处理终端和webSocket发送来的消息
     * @param client
     * @param msg
     */
    public void process(Channel client, IMMessage msg){
        //这边做统一处理
        if(null == msg) {
            return;
        }
        if(msg.getCmd().equals(IMProtocol.LOGIN.getName())){
            // 保持用户的IP地址
            // 保存用户的昵称，端口，终端类型
            // 把用户保存到一个容器中
            client.attr(ChannelSupervise.NICK_NAME).getAndSet(msg.getSender());
            client.attr(ChannelSupervise.IP_ADDR).getAndSet(client.remoteAddress().toString().replace("/",""));
            client.attr(ChannelSupervise.FROM).getAndSet(msg.getTerminal());

            ChannelSupervise.addChannel(client);

            // 通知所有用户谁上线了
            for (Channel channel:ChannelSupervise.GlobalGroup){
                boolean isSelf = (channel == client);
                if(!isSelf){
                    msg = new IMMessage(IMProtocol.SYSTEM.getName(),sysTime(),ChannelSupervise.GlobalGroup.size(),getNickName(client)+"加入!");
                }else {
                    msg = new IMMessage(IMProtocol.SYSTEM.getName(),sysTime(),ChannelSupervise.GlobalGroup.size(),"已于服务器建立连接");
                }

                if("Console".equals(channel.attr(ChannelSupervise.FROM).get())){
                    channel.writeAndFlush(msg);
                    continue;
                }

                String content = imEncoder.encode(msg);
                channel.writeAndFlush(new TextWebSocketFrame(content));
            }

        }else if(msg.getCmd().equals(IMProtocol.LOGIN.getName())){

        }
    }

    /**
     * 获取系统时间
     * @return
     */
    private long sysTime(){
        return System.currentTimeMillis();
    }


    /**
     * 从channel中获取昵称
     * @param channel
     * @return
     */
    private String getNickName(Channel channel){
      return  channel.attr(ChannelSupervise.NICK_NAME).get();
    }
}
