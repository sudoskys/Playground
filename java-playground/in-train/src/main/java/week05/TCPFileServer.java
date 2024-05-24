package week05;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFileServer {
    private int port = 9000;

    public TCPFileServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected...");
                new Thread(() -> {
                    try {
                        InputStream in = clientSocket.getInputStream();
                        FileOutputStream fileOut = new FileOutputStream("received.txt");
                        BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOut);
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
                            bufferedOut.write(buffer, 0, bytesRead);
                            bufferedOut.flush();
                        }
                        System.out.println("File received from client");
                        clientSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new TCPFileServer(9000).start();
    }
}