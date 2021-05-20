package org.kzhou.netty.chat.protocol;

import org.msgpack.annotation.Message;

/**
 * 自定义协议对应的编解码实体类
 */
@Message
public class IMMessage {

    private String addr; // IP地址及端口
    private String cmd; // 命令类型
    private long time; // 发送时间
    private int online; // 在线人数
    private String sender;
    private String receiver;
    private String content; // 消息类型
    private String terminal; // 终端类型

    public IMMessage() {
    }


    public IMMessage(String cmd, long time, String sender, String terminal) {
        this.cmd = cmd;
        this.time = time;
        this.sender = sender;
        this.terminal = terminal;
    }

    public IMMessage(String addr, String cmd, long time, int online) {
        this.addr = addr;
        this.cmd = cmd;
        this.time = time;
        this.online = online;
    }

    public IMMessage(String addr, String cmd, long time, int online, String sender, String receiver, String terminal) {
        this.addr = addr;
        this.cmd = cmd;
        this.time = time;
        this.online = online;
        this.sender = sender;
        this.receiver = receiver;
        this.terminal = terminal;
    }


    public IMMessage(String cmd, long time, int online, String sender) {
        this.cmd = cmd;
        this.time = time;
        this.online = online;
        this.sender = sender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
