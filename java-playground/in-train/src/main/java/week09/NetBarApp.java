package week09;

/*
网吧管理系统
数据库设计和创建(JDBC)
1. 表结构设计
   - 卡表（卡号, 密码, 身份证号, 状态, 余额）
   - 机器表（机器号, 状态）
   - 上网记录表（卡号, 机器号, 上机时间, 下机时间, 上机时长）
2. 创建数据库
添加初始数据的SQL语句
```sql
CREATE DATABASE netbar;
USE netbar;

CREATE TABLE card (
    card_id INT PRIMARY KEY,
    password VARCHAR(20),
    id_card VARCHAR(20),
    status INT,
    balance DECIMAL(10, 2)
);

CREATE TABLE machine (
    machine_id INT PRIMARY KEY,
    status INT
);

CREATE TABLE record (
    card_id INT,
    machine_id INT,
    start_time DATETIME,
    end_time DATETIME,
    duration INT,
    fee DECIMAL(10, 2)
);

INSERT INTO card VALUES (1, '123456', '1234567890', 0, 100.00);
INSERT INTO card VALUES (2, '123456', '1234567891', 0, 200.00);

INSERT INTO machine VALUES (1, 0);
INSERT INTO machine VALUES (2, 0);

INSERT INTO record VALUES (1, 1, '2021-01-01 10:00:00', '2021-01-01 11:00:00', 60);
INSERT INTO record VALUES (2, 2, '2021-01-01 10:00:00', '2021-01-01 11:00:00', 60);
```
3. 实现网吧管理系统GUI Swing编程
   - 网吧管理系统主要功能
     - 上机
     - 下机
   - 网吧管理系统实现
   - 上机
     - 卡号和密码的校验
     - 卡的余额的检验
     - 获取当前时间作为上机时间
     - 记录上机操作
     - 修改机器状态
   - 下机
     - 选择被使用的机器号
     - 获取当前时间作为下机时间
     - 计算费用
     - 扣费
     - 记录下机操作
     - 修改机器状态

* */

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class NetBarApp extends JFrame {
    // Database configuration
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgresUser";
    private static final String DB_PASSWORD = "postgresPW";
    // Logger
    private static final Logger logger = Logger.getLogger(NetBarApp.class.getName());
    // ComboBox for machine IDs
    private final JComboBox<Integer> machineIdComboBox = new JComboBox<>();
    // Database connection
    private Connection connection;

    public NetBarApp() {
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
        setSize(400, 300);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Tabs for different sections: 上机, 下机, 帮助
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("上机", createLoginPanel());
        tabbedPane.addTab("下机", createLogoutPanel());
        tabbedPane.addTab("帮助", createHelpPanel());

        container.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createLoginPanel() {
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

        return panel;
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

            refreshMachineIds(machineIdComboBox);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "上机操作失败", ex);
            JOptionPane.showMessageDialog(this, "上机操作失败", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createLogoutPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel machineIdLabel = new JLabel("选择机器号:");
        machineIdComboBox.setPreferredSize(new Dimension(100, 30));
        JButton logoutButton = new JButton("下机");
        logoutButton.addActionListener(e -> logout(machineIdComboBox));
        panel.add(machineIdLabel, BorderLayout.WEST);
        panel.add(machineIdComboBox, BorderLayout.CENTER);
        panel.add(logoutButton, BorderLayout.EAST);
        loadMachineIds(machineIdComboBox);
        return panel;
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

            // Insert the logout record with duration and fee
            String insertLogoutRecordSQL = "INSERT INTO record (card_id, machine_id, start_time, end_time, duration, fee) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertLogoutRecordStmt = connection.prepareStatement(insertLogoutRecordSQL);
            insertLogoutRecordStmt.setInt(1, cardId);
            insertLogoutRecordStmt.setInt(2, machineId);
            insertLogoutRecordStmt.setTimestamp(3, startTime);
            insertLogoutRecordStmt.setTimestamp(4, endTime);
            insertLogoutRecordStmt.setLong(5, durationInMinutes);
            insertLogoutRecordStmt.setDouble(6, totalFee);
            insertLogoutRecordStmt.executeUpdate();

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

    private JPanel createHelpPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea helpText = new JTextArea("帮助信息:\n1. 上机: 输入卡号和密码，选择空闲的机器。\n2. 下机: 选择正在使用的机器，计算并扣除费用。");
        helpText.setEditable(false);
        panel.add(new JScrollPane(helpText), BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NetBarApp().setVisible(true));
    }
}