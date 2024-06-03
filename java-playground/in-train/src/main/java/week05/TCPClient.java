package week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9000);
            System.out.println("Connected to server...");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("Hello, Server!");
            String serverResponse = in.readLine();
            System.out.println("Received message from server: " + serverResponse);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}