package org.kzhou.netty.chat.protocol;

/**
 * @Description: 聊天自定义协议
 * @author: Admin
 * @date: 2021年05月08日 10:11
 */
public enum  IMProtocol {

    /**
     * 系统指令
     */
    SYSTEM("SYSTEM"),

    /**
     * 交互指令
     */
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    CHAT("CHAT"),
    FLOWER("FLOWER");

    private String name;

    IMProtocol(String name){
        this.name = name;
    }

    public static boolean isIMProtocol(String content){
        // [开头 ]结尾 并且包含SYSTEM|LOGIN|LOGOUT|CHAT|FLOWER。 我们认为是自定义协议
       return content.matches("^\\[(SYSTEM|LOGIN|LOGOUT|CHAT|FLOWER)\\]");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
