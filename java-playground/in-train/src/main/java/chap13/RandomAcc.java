package chap13;

import java.io.*;
import java.nio.channels.FileChannel;

public class RandomAcc {

    public static void main(String[] args) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile("src/main/java/chap13/RandomAcc.java", "r"); RandomAccessFile raf2 = new RandomAccessFile("RandomAcc_copy.java", "rw");) {
            System.out.println("文件指针的初始位置：" + raf.getFilePointer());
            raf.seek(0);
            byte[] buf = new byte[1024];
            int hasRead = 0;
            while ((hasRead = raf.read(buf)) > 0) {
                System.out.print(new String(buf, 0, hasRead));
            }
            FileChannel fileChannel = raf.getChannel();
            System.out.println("文件通道的大小：" + fileChannel.size());
            FileChannel fileChannel2 = raf2.getChannel();
            long trans = fileChannel.transferTo(0, fileChannel.size(), fileChannel2);
            if (trans > 0) {
                System.out.println("文件复制成功！");
            }
            // Close
            fileChannel.close();
            fileChannel2.close();
        }
    }
}
