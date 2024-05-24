package week05;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPServer {
    private static final int PORT = 9000;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);
            while (true) {
                new Thread(new ServerThread(serverSocket.accept())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class ServerThread implements Runnable {
    private final java.net.Socket socket;

    public ServerThread(java.net.Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected: " + socket.getInetAddress());
            // 获取客户端的输入流
            byte[] bytes = new byte[1024];
            int len = socket.getInputStream().read(bytes);
            System.out.println("Client: " + new String(bytes, 0, len));
            // 向客户端发送消息
            socket.getOutputStream().write("Hello, this is a message from server.".getBytes());
            socket.getOutputStream().flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}