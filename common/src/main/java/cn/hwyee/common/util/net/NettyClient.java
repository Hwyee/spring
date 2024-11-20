package cn.hwyee.common.util.net;

import gov.nist.javax.sip.stack.SIPClientTransaction;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hui
 * @version 1.0
 * @className NettyClient
 * @description
 * @date 2024/11/20
 * @since JDK 1.8
 */
@Slf4j
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new ChannelInitializer<>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                System.out.println("client connected.");
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new ClientHandler());

            }
        }).option(ChannelOption.SO_BACKLOG,1);

        ChannelFuture connect = bootstrap.connect("127.0.0.1", 7788).sync();

    }



}

class ClientHandler implements ChannelHandler {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.write("client");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}