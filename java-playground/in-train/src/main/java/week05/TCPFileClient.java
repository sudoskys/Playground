package week05;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPFileClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9000);
            System.out.println("Connected to server...");
            FileInputStream fileIn = new FileInputStream("send.txt");
            BufferedInputStream bufferedIn = new BufferedInputStream(fileIn);
            OutputStream out = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedIn.read(buffer, 0, buffer.length)) > 0) {
                out.write(buffer, 0, bytesRead);
                out.flush();
            }
            System.out.println("File sent to server");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

