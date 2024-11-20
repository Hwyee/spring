package cn.hwyee.common.util.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hui
 * @version 1.0
 * @className NettyServer
 * @description
 * @date 2024/11/20
 * @since JDK 1.8
 */
@Slf4j
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup parentELP = new NioEventLoopGroup();
        NioEventLoopGroup childELP = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(parentELP,childELP).handler(new ChannelHandler() {
            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                System.out.println("server add");
            }

            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                System.out.println("server remove");

            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("server ex");

            }
        }).childHandler(new ChannelInitializer<>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();

            }
        });
        serverBootstrap.channel(NioServerSocketChannel.class);
        ChannelFuture sync = serverBootstrap.bind("127.0.0.1", 7788).sync();




    }
}


class ServerHandler implements ChannelHandler {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}