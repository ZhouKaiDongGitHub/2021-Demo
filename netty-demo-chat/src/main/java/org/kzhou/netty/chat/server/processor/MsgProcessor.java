package org.kzhou.netty.chat.server.processor;

import com.alibaba.fastjson.JSONObject;
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

        }else if(msg.getCmd().equals(IMProtocol.CHAT.getName())){

        }else if(msg.getCmd().equals(IMProtocol.FLOWER.getName())){

            JSONObject attrs = getAttrs(client);
            long currentTime = sysTime();
            if(null != attrs){
                long lastTime = attrs.getLongValue("LastFlowerTime");
                int seconds  = 10;
                long subTime = currentTime - lastTime;
                if(subTime < 1000 * seconds){
                    msg.setSender("you");
                    msg.setCmd(IMProtocol.SYSTEM.getName());
                    msg.setContent("您送鲜花太频繁了，请"+ (seconds  - Math.round(subTime/1000))+"s后重试!");
                    String content = imEncoder.encode(msg);
                    client.writeAndFlush(new TextWebSocketFrame(content));
                    return;
                }
            }

            // 正常送花
            for(Channel channel:ChannelSupervise.GlobalGroup){
                if(channel == client){
                    msg.setSender("you");
                    msg.setContent("你送给大家一波鲜花雨");
                    setAttrs(client,"LastFlowerTime",currentTime);
                }else {
                    msg.setSender(getNickName(client));
                    msg.setContent(getNickName(client)+"送来了一波鲜花");
                }
                msg.setTime(sysTime());

                String content = imEncoder.encode(msg);
                client.writeAndFlush(new TextWebSocketFrame(content));
            }
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

    /**
     * 从channel中获取鲜花属性
     * @param channel
     * @return
     */
    private JSONObject getAttrs(Channel channel){
        return channel.attr(ChannelSupervise.ATTRS).get();
    }

    private void setAttrs(Channel channel,String lastFlowerTime,long time){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(lastFlowerTime,time);
        channel.attr(ChannelSupervise.ATTRS).getAndSet(jsonObject);
    }
}
