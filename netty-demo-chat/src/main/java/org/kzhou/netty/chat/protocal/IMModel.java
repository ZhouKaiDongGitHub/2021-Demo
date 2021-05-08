package org.kzhou.netty.chat.protocal;

/**
 * @Description: 聊天model
 * @author: Admin
 * @date: 2021年05月08日 10:11
 */
public class IMModel {

    /**
     * 客户端和服务端消息的格式定义为：指令|时间|发送人|接收人|发送内容
     * 消息头：指令|时间|发送人|接收人
     * 消息体：发送内容
     * 自定义协议是在TCP基础上
     */
    private String instructionType;
    private long time;
    private String sendUser;
    private String acceptUser;
    private String content;

    public String getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(String instructionType) {
        this.instructionType = instructionType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(String acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
