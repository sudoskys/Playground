package ui;

import dao.User;
import dao.UserDAO;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class GameClient {
    private JFrame frame;
    private JTextField usernameField, searchField;
    private JPasswordField passwordField;
    private JTable userTable;
    private UserDAO userDAO = new UserDAO();
    private User loggedInUser = null;
    private UserTableModel userTableModel;

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());


    public GameClient() {
        try {
            MaterialLookAndFeel material = new MaterialLookAndFeel(new MaterialOrientalFontsTheme());
            UIManager.setLookAndFeel(material);
        } catch (UnsupportedLookAndFeelException e) {
            logger.warning("Failed to initialize MaterialLookAndFeel: " + e.getMessage());
        }
        showLoginPage();
    }

    private void showLoginPage() {
        frame = new JFrame("登录");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 添加边距

        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // 自定义字体
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14)); // 自定义字体
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new LoginAction());
        panel.add(loginButton);

        JButton registerButton = new JButton("注册");
        registerButton.addActionListener(e -> showRegisterPage());
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void showRegisterPage() {
        JFrame registerFrame = new JFrame("注册");
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(new JLabel("用户名:"));
        JTextField regUsernameField = new JTextField();
        panel.add(regUsernameField);

        panel.add(new JLabel("密码:"));
        JPasswordField regPasswordField = new JPasswordField();
        panel.add(regPasswordField);

        JButton registerButton = getjButton(regUsernameField, regPasswordField, registerFrame);
        panel.add(registerButton);

        JButton backButton = new JButton("返回");
        backButton.addActionListener(e -> registerFrame.dispose());
        panel.add(backButton);

        registerFrame.add(panel);
        registerFrame.setVisible(true);
    }

    private JButton getjButton(JTextField regUsernameField, JPasswordField regPasswordField, JFrame registerFrame) {
        JButton registerButton = new JButton("注册");
        registerButton.addActionListener(e -> {
            try {
                String username = regUsernameField.getText();
                String password = new String(regPasswordField.getPassword());
                userDAO.addUser(new User(username, password));
                JOptionPane.showMessageDialog(registerFrame, "注册成功");
                registerFrame.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(registerFrame, "注册时出错: " + ex.getMessage());
            }
        });
        return registerButton;
    }

    private void showUserManagementPage() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setTitle("游戏积分管理 - 登录为 " + loggedInUser.getUsername());

        JPanel panel = new JPanel(new BorderLayout());

        // 上部搜索栏
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchField = new JTextField();
        JButton searchButton = new JButton("搜索");
        searchButton.addActionListener(new SearchAction());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        panel.add(searchPanel, BorderLayout.NORTH);

        // 中部表格
        userTableModel = new UserTableModel();
        userTable = new JTable(userTableModel);
        userTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {     // 检测双击
                    int selectedRow = userTable.getSelectedRow();
                    if (selectedRow != -1) {
                        User user = userTableModel.getUserAt(selectedRow);
                        showEditDialog(user);
                    }
                }
            }
        });
        refreshUserList(null);

        // 右键菜单
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("修改");
        editItem.addActionListener(new EditAction());
        JMenuItem deleteItem = new JMenuItem("删除");
        deleteItem.addActionListener(new DeleteAction());
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        userTable.setComponentPopupMenu(popupMenu);
        userTable.addMouseListener(new TableMouseListener(userTable));

        panel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        // 下部登出按钮
        JButton logoutButton = new JButton("登出");
        logoutButton.addActionListener(e -> {
            loggedInUser = null;
            frame.dispose();
            new GameClient();
        });
        panel.add(logoutButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = userDAO.getUserByUsername(username);
                if (user != null && user.getPassword().equals(password)) {
                    loggedInUser = user;
                    showUserManagementPage();
                } else {
                    JOptionPane.showMessageDialog(frame, "用户名或密码错误");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "登录时出错: " + ex.getMessage());
            }
        }
    }

    private class SearchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = searchField.getText();
            refreshUserList(searchText);
        }
    }

    private class EditAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) userTableModel.getValueAt(selectedRow, 0);
                User user = userTableModel.getUserAt(selectedRow);
                showEditDialog(user);
            }
        }
    }

    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) userTableModel.getValueAt(selectedRow, 0);
                try {
                    userDAO.deleteUser(id);
                    refreshUserList(null);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "删除用户时出错: " + ex.getMessage());
                }
            }
        }
    }

    private void showEditDialog(User user) {
        JDialog editDialog = new JDialog(frame, "修改用户", true);
        editDialog.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("用户名:"));
        JTextField usernameField = new JTextField(user.getUsername());
        panel.add(usernameField);

        panel.add(new JLabel("密码:"));
        JPasswordField passwordField = new JPasswordField(user.getPassword());
        panel.add(passwordField);

        panel.add(new JLabel("积分:"));
        JTextField scoreField = new JTextField(String.valueOf(user.getScore()));
        panel.add(scoreField);

        JButton updateButton = new JButton("更新");
        updateButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String scoreText = scoreField.getText();

                // Validate user input
                if (username.isEmpty() || password.isEmpty() || scoreText.isEmpty()) {
                    JOptionPane.showMessageDialog(editDialog, "所有字段都必须填写");
                    return;
                }

                int score;
                try {
                    score = Integer.parseInt(scoreText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(editDialog, "积分必须是有效的整数");
                    return;
                }

                user.setUsername(username);
                user.setPassword(password);
                user.setScore(score);
                userDAO.updateUser(user);
                refreshUserList(null);
                editDialog.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(editDialog, "更新用户信息时出错: " + ex.getMessage());
            }
        });
        panel.add(updateButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> editDialog.dispose());
        panel.add(cancelButton);

        editDialog.add(panel);
        editDialog.setVisible(true);
    }

    private void refreshUserList(String searchText) {
        try {
            List<User> users;
            if (searchText == null || searchText.trim().isEmpty()) {
                users = userDAO.getAllUsers();
            } else {
                users = userDAO.searchUsersByUsername(searchText);
            }
            userTableModel.setUsers(users);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "获取用户列表时出错: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameClient::new);
    }
}

class UserTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "用户名", "密码", "积分"};
    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return users == null ? 0 : users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> user.getId();
            case 1 -> user.getUsername();
            case 2 -> user.getPassword();
            case 3 -> user.getScore();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public User getUserAt(int rowIndex) {
        return users.get(rowIndex);
    }
}

class TableMouseListener extends MouseAdapter {
    private JTable table;

    public TableMouseListener(JTable table) {
        this.table = table;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
    }

    private void showPopup(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        table.setRowSelectionInterval(row, row);
        JPopupMenu popup = table.getComponentPopupMenu();
        if (popup != null) {
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}