package org.kzhou.netty.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IM解码器
 */
public class IMDecoder extends ByteToMessageDecoder {

    private Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s\\-\\s(.*))?");

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        final int length = byteBuf.readableBytes();
        final byte[] msgBytes  = new byte[length];
        // step1:网络传输过来的二进制变成字符串
        String msg = new String(msgBytes,byteBuf.readerIndex(),length);

        // step2:判断传输过来的字符串是否是我们自定义协议内容
        if(null == msg || "".equals(msg.trim()) || !IMProtocol.isIMProtocol(msg)){
            channelHandlerContext.pipeline().remove(this);
            return;
        }

        // step3:将二进制直接变为对象，利用messagePack包
        byteBuf.getBytes(byteBuf.readerIndex(),msgBytes,0,length);
        list.add(new MessagePack().read(msgBytes,IMMessage.class));
        byteBuf.clear();
    }

    public IMMessage decode(String msg){
        if(null == msg || "".equals(msg.trim())){
            return null;
        }
        // 解析字符串最好的办法就是正则处理
        Matcher m = pattern.matcher(msg);
        String header = "";
        String content = "";
        if(m.matches()){
            header = m.group(1);
            content = m.group(3);
        }
        String[] headers = header.split("\\]\\[");
        long time = Long.parseLong(headers[1]);
        String nickName = headers[2];
        if(msg.startsWith("["+IMProtocol.LOGIN.getName()+"]")){
            return new IMMessage(headers[0],time,headers[3],nickName);
        }else if(msg.startsWith("["+IMProtocol.CHAT.getName()+"]")){
            return new IMMessage(headers[0],time,headers[3],nickName);
        }else if(msg.startsWith("["+IMProtocol.FLOWER.getName()+"]")){
            return new IMMessage(headers[0],time,headers[3],nickName);
        }else {
            return null;
        }
    }
}
