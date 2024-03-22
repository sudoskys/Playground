package chap13;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        // 接收输入的字符串并解析出源文件路径和目标文件路径
        Scanner scanner = new Scanner(System.in);
        String sourcePath = scanner.next();
        String targetPath = scanner.next();
        // 使用 try-with-resources 语句确保流的关闭
        try (InputStream input = new FileInputStream(sourcePath); OutputStream output = new FileOutputStream(targetPath)) {
            // 创建一个字节数组作为缓冲区
            byte[] buffer = new byte[1024];
            int bytesRead;

            // 读取源文件内容并写入目标文件
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        // 执行 shell 命令，cp /test/c.txt /test/d.txt 会将 c.txt 复制到 d.txt
        List<String> command = new ArrayList<>();
        command.add("cp");
        command.add(sourcePath);
        command.add(targetPath);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.start();

        // echo "java" > /test/c.txt
        List<String> command2 = new ArrayList<>();
        command2.add("echo");
        command2.add("java");
        command2.add(">");
        command2.add(sourcePath);
        ProcessBuilder processBuilder2 = new ProcessBuilder(command2);
        processBuilder2.start();
    }
}