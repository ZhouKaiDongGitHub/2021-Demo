package org.kzhou.netty.chat.server;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.fasterxml.jackson.core.JsonParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;
import org.kzhou.netty.chat.protocal.IMModel;

import java.util.List;

/**
 * @Description: IM聊天室解码
 * @author: Admin
 * @date: 2021年05月08日 11:05
 */
public class IMServerDecode extends ByteToMessageDecoder {

    private final Logger logger= Logger.getLogger(IMServerDecode.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // step1:将字节转换为字符串
        int length = in.readerIndex();
        byte[] bytes = new byte[length];
        in.getBytes(length,bytes);
        String msg = new String(bytes, 0, bytes.length, "utf-8");
        logger.info("接收到数据msg:"+msg);

        // step2:将字符串转换为对象
        String[] msgArray  = msg.split("|");
        String instructionType = msgArray[0];
        long time = Long.valueOf(msgArray[1]);
        String sendUser = msgArray[2];
        String acceptUser = msgArray[3];
        String content = msgArray[4];

        IMModel imModel = new IMModel();
        imModel.setInstructionType(instructionType);
        imModel.setTime(time);
        imModel.setSendUser(sendUser);
        imModel.setAcceptUser(acceptUser);
        imModel.setContent(content);
        logger.info("将msg数据转化为对象信息:"+ JSONObject.toJSONString(imModel));

        // step3:将对象信息写到out中
        out.add(imModel);
    }
}
