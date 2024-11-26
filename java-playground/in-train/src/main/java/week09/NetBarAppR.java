package week09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class NetBarAppR extends JFrame {
    // Database configuration
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgresUser";
    private static final String DB_PASSWORD = "postgresPW";
    // Logger
    private static final Logger logger = Logger.getLogger(NetBarApp.class.getName());
    // Database connection
    private Connection connection;

    public NetBarAppR() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            // Logger
            logger.log(Level.SEVERE, "无法连接到数据库", e);
            JOptionPane.showMessageDialog(this, "无法连接到数据库", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        initializeUI();
    }

    private void initializeUI() {
        setTitle("网吧管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new GridLayout(3, 1, 10, 10));

        JButton loginButton = new JButton("上机");
        JButton logoutButton = new JButton("下机");
        JButton helpButton = new JButton("帮助");

        loginButton.addActionListener(e -> new LoginWindow(connection, logger));
        logoutButton.addActionListener(e -> new LogoutWindow(connection, logger));
        helpButton.addActionListener(e -> new HelpWindow());

        container.add(loginButton);
        container.add(logoutButton);
        container.add(helpButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetBarApp().setVisible(true));
    }
}

class LoginWindow extends JFrame {
    private final Connection connection;
    private final Logger logger;

    public LoginWindow(Connection connection, Logger logger) {
        this.connection = connection;
        this.logger = logger;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("上机");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel cardIdLabel = new JLabel("卡号:");
        JTextField cardIdField = new JTextField();
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("上机");
        loginButton.addActionListener(e -> login(cardIdField, passwordField));

        panel.add(cardIdLabel);
        panel.add(cardIdField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void login(JTextField cardIdField, JPasswordField passwordField) {
        String cardId = cardIdField.getText();
        String password = new String(passwordField.getPassword());

        try {
            String checkCardSQL = "SELECT balance FROM card WHERE card_id = ? AND password = ?";
            PreparedStatement checkCardStmt = connection.prepareStatement(checkCardSQL);
            checkCardStmt.setInt(1, Integer.parseInt(cardId));
            checkCardStmt.setString(2, password);
            ResultSet rs = checkCardStmt.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "卡号或密码不正确", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double balance = rs.getDouble("balance");
            if (balance <= 0) {
                JOptionPane.showMessageDialog(this, "余额不足，请充值", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Find an available machine
            String findMachineSQL = "SELECT machine_id FROM machine WHERE status = 0 LIMIT 1";
            Statement findMachineStmt = connection.createStatement();
            ResultSet machineRs = findMachineStmt.executeQuery(findMachineSQL);

            if (!machineRs.next()) {
                JOptionPane.showMessageDialog(this, "没有空闲的机器", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int machineId = machineRs.getInt("machine_id");
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime = sdf.format(now);

            // Record the login operation
            String updateMachineSQL = "UPDATE machine SET status = 1 WHERE machine_id = ?";
            PreparedStatement updateMachineStmt = connection.prepareStatement(updateMachineSQL);
            updateMachineStmt.setInt(1, machineId);
            updateMachineStmt.executeUpdate();

            String insertRecordSQL = "INSERT INTO record (card_id, machine_id, start_time) VALUES (?, ?, ?)";
            PreparedStatement insertRecordStmt = connection.prepareStatement(insertRecordSQL);
            insertRecordStmt.setInt(1, Integer.parseInt(cardId));
            insertRecordStmt.setInt(2, machineId);
            insertRecordStmt.setTimestamp(3, Timestamp.valueOf(startTime));
            insertRecordStmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "上机成功！机器号: " + machineId, "信息", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "上机操作失败", ex);
            JOptionPane.showMessageDialog(this, "上机操作失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class LogoutWindow extends JFrame {
    private final Connection connection;
    private final Logger logger;
    private final JComboBox<Integer> machineIdComboBox = new JComboBox<>();

    public LogoutWindow(Connection connection, Logger logger) {
        this.connection = connection;
        this.logger = logger;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("下机");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel machineIdLabel = new JLabel("选择机器号:");
        machineIdComboBox.setPreferredSize(new Dimension(100, 30));
        JButton logoutButton = new JButton("下机");
        logoutButton.addActionListener(e -> logout(machineIdComboBox));

        panel.add(machineIdLabel, BorderLayout.WEST);
        panel.add(machineIdComboBox, BorderLayout.CENTER);
        panel.add(logoutButton, BorderLayout.EAST);

        loadMachineIds(machineIdComboBox);

        add(panel);
        setVisible(true);
    }

    private void loadMachineIds(JComboBox<Integer> machineIdComboBox) {
        try {
            String machineSQL = "SELECT machine_id FROM machine WHERE status = 1";
            Statement machineStmt = connection.createStatement();
            ResultSet rs = machineStmt.executeQuery(machineSQL);

            while (rs.next()) {
                machineIdComboBox.addItem(rs.getInt("machine_id"));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "无法加载机器号", e);
            JOptionPane.showMessageDialog(this, "无法加载机器号", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshMachineIds(JComboBox<Integer> machineIdComboBox) {
        machineIdComboBox.removeAllItems();
        loadMachineIds(machineIdComboBox);
    }

    private void logout(JComboBox<Integer> machineIdComboBox) {
        Integer machineId = (Integer) machineIdComboBox.getSelectedItem();
        if (machineId == null) {
            JOptionPane.showMessageDialog(this, "请选择使用中的机器", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Date now = new Date();
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp endTime = new Timestamp(now.getTime());

            // Find the start time and card ID of the latest session for this machine
            String findRecordSQL = "SELECT card_id, start_time FROM record WHERE machine_id = ? AND end_time IS NULL ORDER BY start_time DESC LIMIT 1";
            PreparedStatement findRecordStmt = connection.prepareStatement(findRecordSQL);
            findRecordStmt.setInt(1, machineId);
            ResultSet rs = findRecordStmt.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "未找到上机记录", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cardId = rs.getInt("card_id");
            Timestamp startTime = rs.getTimestamp("start_time");

            // Calculate the duration in minutes
            long durationInMillis = endTime.getTime() - startTime.getTime();
            long durationInMinutes = durationInMillis / (1000 * 60);

            // Calculate the fee based on the duration
            double feePerMinute = 0.1; // 假设每分钟 0.1 元
            double totalFee = durationInMinutes * feePerMinute;

            // Update the record with the end time, duration, and fee
            String updateRecordSQL = "UPDATE record SET end_time = ?, duration = ?, fee = ? WHERE card_id = ? AND machine_id = ? AND start_time = ?";
            PreparedStatement updateRecordStmt = connection.prepareStatement(updateRecordSQL);
            updateRecordStmt.setTimestamp(1, endTime);
            updateRecordStmt.setLong(2, durationInMinutes);
            updateRecordStmt.setDouble(3, totalFee);
            updateRecordStmt.setInt(4, cardId);
            updateRecordStmt.setInt(5, machineId);
            updateRecordStmt.setTimestamp(6, startTime);
            updateRecordStmt.executeUpdate();

            // Deduct balance
            String updateCardSQL = "UPDATE card SET balance = balance - ? WHERE card_id = ?";
            PreparedStatement updateCardStmt = connection.prepareStatement(updateCardSQL);
            updateCardStmt.setDouble(1, totalFee);
            updateCardStmt.setInt(2, cardId);
            updateCardStmt.executeUpdate();

            // Set machine to available
            String updateMachineSQL = "UPDATE machine SET status = 0 WHERE machine_id = ?";
            PreparedStatement updateMachineStmt = connection.prepareStatement(updateMachineSQL);
            updateMachineStmt.setInt(1, machineId);
            updateMachineStmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "下机成功！总费用: " + totalFee + "元", "信息", JOptionPane.INFORMATION_MESSAGE);
            refreshMachineIds(machineIdComboBox);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "下机操作失败", ex);
            JOptionPane.showMessageDialog(this, "下机操作失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class HelpWindow extends JFrame {
    public HelpWindow() {
        setTitle("帮助");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea helpText = new JTextArea("帮助信息:\n1. 上机: 输入卡号和密码，选择空闲的机器。\n2. 下机: 选择正在使用的机器，计算并扣除费用。");
        helpText.setEditable(false);

        add(new JScrollPane(helpText));
        setVisible(true);
    }
}