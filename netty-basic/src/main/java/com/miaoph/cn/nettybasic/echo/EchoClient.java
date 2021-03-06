package com.miaoph.cn.nettybasic.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 创建日期：2018/08/26
 * 类说明：Netty实现的客户端
 */
public class EchoClient {

    private final int port;
    private final String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {
//        /*线程组*/
//        EventLoopGroup group = new NioEventLoopGroup();
//        try{
//            /*客户端启动必备*/
//            Bootstrap b = new Bootstrap();
//            b.group(group)/*把线程组传入*/
//                    /*指定使用NIO进行网络传输*/
//                    .channel(NioSocketChannel.class)
//                    .remoteAddress(new InetSocketAddress(host,port))
//                    .handler(new EchoClientHandle());
//            /*连接到远程节点，阻塞直到连接完成*/
//            ChannelFuture f = b.connect().sync();
//            /*阻塞程序，直到Channel发生了关闭*/
//            f.channel().closeFuture().sync();
//        }finally {
//            group.shutdownGracefully().sync();
//        }
        //线程组
        final NioEventLoopGroup group = new NioEventLoopGroup();

        try{
            //客户端启动必备
            final Bootstrap b = new Bootstrap();
            b.group(group) //把线程组传入，使用指定客户端进行传输
                    .channel(NioSocketChannel.class)
                    //链接远程
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new EchoClientHandle());
            /*连接到远程节点，阻塞直到连接完成*/
            final ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }


    }

  public static void main(String[] args) throws InterruptedException {
       MyLocal local = new MyLocal();
       local.set(200);
       new Thread(()->System.out.printf("%s \n",local.get())).run();
  }
  private static  class  MyLocal extends  ThreadLocal<Integer>{
        @Override
        protected  Integer initialValue(){
            return 100;
        }
  }
}
