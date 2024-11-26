package week08;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient {
    private JFrame frame = new JFrame("聊天室客户端");
    private JTextField textField = new JTextField(40);
    private JTextArea messageArea = new JTextArea(20, 40);
    private JList<String> userList = new JList<>();

    private PrintWriter out;
    private Socket socket;

    public ChatClient(String serverAddress) throws IOException {
        socket = new Socket(serverAddress, 12345);
        out = new PrintWriter(socket.getOutputStream(), true);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        // North - user list
        JScrollPane userScrollPane = new JScrollPane(userList);
        pane.add(userScrollPane, BorderLayout.EAST);

        // Center - messages
        messageArea.setEditable(false);
        JScrollPane messagePane = new JScrollPane(messageArea);
        pane.add(messagePane, BorderLayout.CENTER);

        // South - input box and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);

        JButton sendButton = new JButton("发送");
        JButton cancelButton = new JButton("取消");
        panel.add(sendButton, BorderLayout.EAST);
        panel.add(cancelButton, BorderLayout.WEST);
        pane.add(panel, BorderLayout.SOUTH);

        frame.pack();

        // Event listeners
        textField.addActionListener(e -> sendMessage());
        sendButton.addActionListener(e -> sendMessage());
        cancelButton.addActionListener(e -> textField.setText(""));

        new IncomingReader(socket).start();  // 启动信息接收线程
    }

    private void sendMessage() {
        out.println(textField.getText());
        textField.setText("");
    }

    private class IncomingReader extends Thread {
        private BufferedReader in;

        public IncomingReader(Socket socket) throws IOException {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    messageArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";  // or the server IP
        ChatClient client = new ChatClient(serverAddress);
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
    }
}