package chap13;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class CharRead {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        // 创建 FileReader 对象
        try (Reader reader = new FileReader(str)) {
            int n;
            // 读取文件
            while ((n = reader.read()) != -1) {
                System.out.print((char) n);
            }
        }
    }
}
