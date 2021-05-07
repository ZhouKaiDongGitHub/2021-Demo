package org.kzhou.netty.websocket.client;

import org.apache.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description: WebSocket客户端
 * @author: Admin
 * @date: 2021年05月07日 10:46
 */
public class WebSocketClientImpl extends WebSocketClient{

    private final Logger logger=Logger.getLogger(WebSocketClientImpl.class);

    public WebSocketClientImpl(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("open");
    }

    @Override
    public void onMessage(String s) {
        logger.info("message:"+s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.info("close:"+s);
    }

    @Override
    public void onError(Exception e) {
        logger.info("error:"+e.getMessage());
    }
}
