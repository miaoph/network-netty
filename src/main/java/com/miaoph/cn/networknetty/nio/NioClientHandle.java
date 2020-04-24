package com.miaoph.cn.networknetty.nio;

import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

@Data
public class NioClientHandle implements Runnable {

    private String server;
    private Integer port;
    private volatile boolean started;

    private Selector selector;
    private SocketChannel socketChannel;

    public NioClientHandle(String server, Integer port) {
        this.server = server;
        this.port = port;
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            //设置nio为非阻塞模式
            socketChannel.configureBlocking(false);
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常 退出当前程序
            System.exit(-1);
        }
    }

    public NioClientHandle() {
    }

    public void stop() {
        started = false;
    }

    @Override
    public void run() {
        //尝试连接服务器
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        while (started) {
            try {
                selector.select();  //阻塞方法
//                selector.select(1000L);   //选择轮旋 方式 每1秒 轮询一次
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();//将当前的选择器赋值给 key  并且移除当前迭代器，进行下次迭代
                    iterator.remove();

                    try {
                        handleInput(key); //处理当前的事件
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
             SocketChannel sc = (SocketChannel) key.channel();
            /**
             * 处理连接事件
             */
            if(key.isConnectable()){
                 if(sc.finishConnect()){
                     sc.register(selector,SelectionKey.OP_READ); //客户端 关注读事件
                 }else  System.exit(-1);
             }
            if(key.isReadable()){//关注读事件
                 ByteBuffer buffer = ByteBuffer.allocate(1024);
                 int read = sc.read(buffer); //重通道读取数据
                    if(read >0){// 表示读取到数据

                    }else { //表示连接通道已经关闭，四次挥手
                        key.cancel();
                        sc.close();
                    }
            }
        }
    }

    /**
     * nio 践行连接
     *
     * @throws IOException
     */
    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(server, port))) {
        } else { // 连接失败，表示当前http可能正在建立连接 三次握手  所以连接channel 关注 连接事件
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }
    }
}
