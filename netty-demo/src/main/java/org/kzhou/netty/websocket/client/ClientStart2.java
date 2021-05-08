package org.kzhou.netty.websocket.client;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 客户端启动类
 * @author: Admin
 * @date: 2021年05月07日 10:59
 */
public class ClientStart2 {

    public static void main(String[] args) {
        try {
            WebSocketClientImpl webSocketClient = new WebSocketClientImpl("ws://localhost:8081/websocket");
            webSocketClient.connect();
            int i = 1 ;
            while (true){
                TimeUnit.MILLISECONDS.sleep(1000*5*10);
                String msg = "测试数据："+(i++);
                webSocketClient.send(msg);
                System.out.println("发生消息："+msg);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
