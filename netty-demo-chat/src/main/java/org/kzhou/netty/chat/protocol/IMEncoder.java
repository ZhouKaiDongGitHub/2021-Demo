package org.kzhou.netty.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * IM协议编码器
 */
public class IMEncoder extends MessageToByteEncoder<IMMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IMMessage imMessage, ByteBuf byteBuf) throws Exception {
        // 将对象直接转换为二进制流
        byteBuf.writeBytes(new MessagePack().write(imMessage));
    }

    public String encode(IMMessage imMessage){
        if(null == imMessage){
            return "";
        }
        String prex = "[" + imMessage.getCmd() + "]" + "[" + imMessage.getTime() + "]";
        if(IMProtocol.LOGIN.getName().equals(imMessage.getCmd()) || IMProtocol.FLOWER.getName().equals(imMessage.getCmd())){
            prex +=  "[" + imMessage.getSender() + "]" + "[" + imMessage.getTerminal() + "]";
        }else if(IMProtocol.CHAT.getName().equals(imMessage.getCmd())){
            prex +=  "[" + imMessage.getSender() + "]";
        }else if(IMProtocol.SYSTEM.getName().equals(imMessage.getCmd())){
            prex +=  "[" + imMessage.getOnline() + "]";
        }
        if(!(null==imMessage.getContent()||"".equals(imMessage.getContent()))){
            prex += " - "+imMessage.getContent();
        }
        return prex;
    }
}
