package chap13;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class CharWrite {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strs = str.split("，");
        String filename = strs[0];
        String sr = strs[1];
        // 创建 FileWriter 对象    /test/a.txt，hellojava
        try (Writer write = new FileWriter(filename)) {
            // 向文件中写入字符流
            write.write(sr);
            write.flush();
        }
    }
}
