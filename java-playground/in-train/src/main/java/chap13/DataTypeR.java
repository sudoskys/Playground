package chap13;

import java.io.DataInputStream;
import java.io.*;
import java.util.function.Function;

public class DataTypeR {
    public static void main(String[] args) {
        // 创建一个字节数组输出流，用于存储数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 创建一个DataOutputStream，将数据写入到字节数组输出流中
        try (DataOutputStream dos = new DataOutputStream(baos)) {
            dos.writeInt(123);
            dos.writeDouble(123.45);
            dos.writeBoolean(true);
            dos.writeUTF("Hello, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建一个字节数组输入流，从字节数组输出流中读取数据
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        // 创建一个DataInputStream，从字节数组输入流中读取数据
        try (DataInputStream dis = new DataInputStream(bais)) {
            int i = dis.readInt();
            double d = dis.readDouble();
            boolean b = dis.readBoolean();
            String s = dis.readUTF();

            System.out.println("Read values: " + i + ", " + d + ", " + b + ", " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

