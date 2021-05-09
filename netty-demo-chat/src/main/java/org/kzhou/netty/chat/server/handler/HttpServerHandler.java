package org.kzhou.netty.chat.server.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    //获取class路径
    private URL baseUrl = HttpServerHandler.class.getResource("");
    private final String root = "static";


    private File getResources(String fileName) throws URISyntaxException {

        String basePath = baseUrl.toURI().toString();
        int start = basePath.indexOf("classes/");
        basePath = (basePath.substring(0,start) + "/" + "classes/" ).replaceAll("/+","");

        String path = basePath + root + "/" + fileName;
        path = !path.contains("file:") ? path : path.substring(5);
        path.replaceAll("//","/");

        return new File(path);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {

        String uri = fullHttpRequest.getUri();

        RandomAccessFile file = null;
        String page = uri.equals("/") ? "chat.html" : uri;
        file = new RandomAccessFile(getResources(page),"r");

        HttpResponse response =  new DefaultHttpResponse(fullHttpRequest.getProtocolVersion(), HttpResponseStatus.OK);
        String contentType = "text/html";
        if(uri.endsWith(".css")){
            contentType = "text/css";
        }else if(uri.endsWith(".js")){
            contentType = "text/javascript";
        }else if(uri.toLowerCase().matches(".*\\.(jpg|png|gif)$")){
            String ext = uri.substring(uri.lastIndexOf("."));
            contentType = "image/" + ext;
        }

        response.headers().set(HttpHeaders.Names.CONTENT_TYPE,contentType + "charset=utf-8");

        boolean keepAlive = HttpHeaders.isKeepAlive(response);
        if(keepAlive){
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,file.length());
            response.headers().set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE);
        }

        channelHandlerContext.write(response);
        channelHandlerContext.write(new DefaultFileRegion(file.getChannel(),0,file.length()));

        ChannelFuture future = channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

        if(!keepAlive){
            future.addListener(ChannelFutureListener.CLOSE);
        }

        file.close();
    }
}
