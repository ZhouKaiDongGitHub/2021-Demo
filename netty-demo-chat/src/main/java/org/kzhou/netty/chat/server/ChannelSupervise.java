package org.kzhou.netty.chat.server;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description: channel监控，用于保持客户端的链接
 * @author: Admin
 * @date: 2021年05月07日 10:30
 *
 *
 * 当有客户端连接时候会被channelActive监听到，当断开时会被channelInactive监听到，
 * 一般在这两个方法中去保存/移除客户端的通道信息，而通道信息保存在ChannelSupervise中：
 */
public class ChannelSupervise {

    // Netty专门用来保存客户端信息的容器
    public   static ChannelGroup GlobalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 定义一些扩展属性
    public static final AttributeKey<String> NICK_NAME = AttributeKey.valueOf("nickName");
    public static final AttributeKey<String> IP_ADDR = AttributeKey.valueOf("ipAddr");
    public static final AttributeKey<JSONObject> ATTRS = AttributeKey.valueOf("attrs");
    public static final AttributeKey<String> FROM = AttributeKey.valueOf("from");


    public  static void addChannel(Channel channel){
        GlobalGroup.add(channel);
    }

    public static void removeChannel(Channel channel){
        GlobalGroup.remove(channel);
    }
}
