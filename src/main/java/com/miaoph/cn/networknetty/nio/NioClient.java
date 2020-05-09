package com.miaoph.cn.networknetty.nio;

/**
 * nio 客户端
 */
public class NioClient {
    private static  NioClientHandle nioClientHandle;

    public  static  void start(){
        if(nioClientHandle != null){
            nioClientHandle.stop();
//            nioClientHandle=new NioClientHandle(DEFAULT_SERVER_IP,DEFAULT_SERVER_PORT);
        }
    }

}
