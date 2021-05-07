package org.kzhou.netty.websocket.client;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 客户端启动类
 * @author: Admin
 * @date: 2021年05月07日 10:59
 */
public class ClientStart1 {

    public static void main(String[] args) {
        try {
            WebSocketClientImpl webSocketClient = new WebSocketClientImpl("ws://localhost:8081/websocket");
            webSocketClient.connect();
            int i = 1 ;
            while (true){
                webSocketClient.send("测试数据："+(i++));
                TimeUnit.MILLISECONDS.sleep(1000*5);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
