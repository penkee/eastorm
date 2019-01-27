package com.eastorm.core.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;

import java.util.Date;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:江东大人
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger= Logger.getLogger(WebSocketServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Override
    public void messageReceived(ChannelHandlerContext ctx,Object msg) throws Exception{
        if(msg instanceof FullHttpRequest){
            handleHttpRequest(ctx,(FullHttpRequest)msg);
        }else if(msg instanceof WebSocketFrame){
            handleWebSocketFrame(ctx,(WebSocketFrame)msg);
            System.out.println("messageReceived");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("channelReadComplete"+ctx.channel().id());
    }

    private void handleHttpRequest(ChannelHandlerContext ctx,
                                   FullHttpRequest req) throws Exception{

        if(!req.getDecoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))){

            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory=new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket",null,false);
        handshaker = wsFactory.newHandshaker(req);

        if(handshaker ==null){
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        }else{
            handshaker.handshake(ctx.channel(),req);
        }
    }

    private void handleWebSocketFrame(final ChannelHandlerContext ctx,WebSocketFrame frame){

        if(frame instanceof CloseWebSocketFrame){
            handshaker.close(ctx.channel(),(CloseWebSocketFrame)frame.retain());
            return;
        }


        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        if(!(frame instanceof TextWebSocketFrame)){
            throw new UnsupportedOperationException(String.format("%s frame types not supported",
                    frame.getClass().getName()));
        }

        String request=((TextWebSocketFrame)frame).text();
        logger.info(String.format("%s received %s",ctx.channel(),request));

        ctx.channel().write(new TextWebSocketFrame(request+"，欢迎使用Netty WebSocket服务，现在时刻："+new Date().toString()));

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ctx.channel().write(new TextWebSocketFrame("我爱你，大堃堃"));
                System.out.println("我爱你，大堃堃");
                ctx.flush();
            }
        }).start();
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req,
                                         FullHttpResponse res){
        if(res.getStatus().code()!=200){
            ByteBuf buf= Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);

            res.content().writeBytes(buf);
            buf.release();

            setContentLength(res, res.content().readableBytes());
        }

        ChannelFuture f =ctx.channel().writeAndFlush(res);
        if(isKeepAlive(req) || res.getStatus().code() !=200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception{
        cause.printStackTrace();
        ctx.close();
    }

}
