package week04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPPlay {
    public static void main(String[] args) {
        try {
            // 创建DatagramSocket对象
            DatagramSocket socket = new DatagramSocket();
            // 要发送的数据
            String message = "Hello, World!";
            // 将数据转换为字节数组
            byte[] buffer = message.getBytes();
            // 获取本地主机的InetAddress对象
            InetAddress address = InetAddress.getLocalHost();
            // 创建DatagramPacket对象
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9000);
            // 使用DatagramSocket的send方法发送数据包
            socket.send(packet);
            // 关闭套接字
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}