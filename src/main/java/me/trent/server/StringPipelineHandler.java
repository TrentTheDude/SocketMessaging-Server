package me.trent.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class StringPipelineHandler extends
        SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(
            "connections", GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        Channel currentChannel = ctx.channel();
        System.out.println("[MESSAGE] - " + currentChannel.remoteAddress() + " - " + message);
        currentChannel.writeAndFlush("[Server] - Success");
        //todo; handle these messages with purpose...
    }


    //@Override
    //public void channelReadComplete(ChannelHandlerContext arg0)
    //        throws Exception {
    //    System.out.println("channelReadComplete");
    //}

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext arg0)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("channelWritabilityChanged");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[SYSTEM] New Connection");
        channels.add(ctx.channel());
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[SYSTEM] Lost Connection");
        channels.remove(ctx.channel());
        super.handlerRemoved(ctx);
    }
/*
    @Override
    public void messageReceived(ChannelHandlerContext ctx, String arg1)
            throws Exception {
        Channel currentChannel = ctx.channel();
        System.out.println("[INFO] - " + currentChannel.remoteAddress() + " - "
                + arg1);
        currentChannel.write("[Server] - Success");

    }

 */

    //@Override
    //public boolean beginMessageReceived(ChannelHandlerContext ctx)
    //        throws Exception {
    //    System.out.println("Message received");
    //    return super.beginMessageReceived(ctx);
    //}


}
