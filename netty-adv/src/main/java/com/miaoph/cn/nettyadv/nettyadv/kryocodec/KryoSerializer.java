package com.miaoph.cn.nettyadv.nettyadv.kryocodec;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author 
 * 类说明：
 */
public class KryoSerializer {
    private static Kryo kryo = KryoFactory.createKryo();

    public static void serialize(Object object, ByteBuf out) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, object);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.writeBytes(b);
    }

    public static Object deserialize(ByteBuf out) {
        if (out == null) {
            return null;
        }
        Input input = new Input(new ByteBufInputStream(out));
        return kryo.readClassAndObject(input);
    }
}