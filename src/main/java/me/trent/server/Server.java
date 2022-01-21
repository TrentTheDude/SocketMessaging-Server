package me.trent.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class Server {

    private String ip;
    private int port;
    private String name;

    public Server(String ip, int port, String name){
        this.ip = ip;
        this.port = port;
        this.name = name;

    }

        public void start() {
        Utils.log("Starting Server...");
            EventLoopGroup producer = new NioEventLoopGroup();
            EventLoopGroup consumer = new NioEventLoopGroup();

            try {

                ServerBootstrap bootstrap = new ServerBootstrap().group(producer, consumer)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ServerPipeline());

                //this.channel = bootstrap.connect(getServer(), getPort()).sync().channel();
                ChannelFuture channel = bootstrap.bind(getIP(), getPort()).sync();
                Utils.log("Server Started! Listening to messages...");
                //channel.channel().closeFuture().sync();

                //ServerBootstrap bootstrap = new ServerBootstrap()
                //        .group(producer, consumer)
                //        .channel(NioServerSocketChannel.class)
                //        .childHandler(new ServerPipeline());

                //bootstrap.bind(getPort()).sync().channel().closeFuture().sync();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //producer.shutdownGracefully();
                //consumer.shutdownGracefully();
            }
        }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getIP() {
        return ip;
    }
}