package chap13;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class IOTry {
    public static void main(String[] args) throws IOException {     //抛出异常
        Scanner path = new Scanner(System.in);
        String str = path.nextLine();
        try (InputStream input = new FileInputStream(str)) {
            // 创建 FileInputStream 对象，读取    /test1/e.txt
            int n;
            // 读取文件
            while ((n = input.read()) != -1) {
                System.out.print((char) n);
            }
        }// 如果输入为空，关闭流
    }
}
