package week04;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostInfo {
    public static void main(String[] args) {
        try {
            // 指定的网址
            String url = "www.baidu.com";

            // 获取 InetAddress 对象
            InetAddress inetAddress = InetAddress.getByName(url);

            // 获取 IP 地址
            String ipAddress = inetAddress.getHostAddress();

            // 获取主机名
            String hostName = inetAddress.getHostName();

            // 输出 IP 地址和主机名
            System.out.println("URL: " + url);
            System.out.println("IP 地址: " + ipAddress);
            System.out.println("主机名: " + hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}