package chap13;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class OutputStreamTry {
    public static void main(String[] args) throws IOException {     //抛出异常
        String[] arr = new Scanner(System.in).nextLine().split("，");
        String str = arr[0];
        String path = arr[1];
        try (OutputStream output = new FileOutputStream(path)) {
            //
            output.write(str.getBytes("UTF-8"));
        }// 如果输入为空，关闭流
    }
}
