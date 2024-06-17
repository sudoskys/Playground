package ui;

import dao.User;
import dao.UserDAO;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 * 游戏客户端
 */
public class GameClient {
    private JFrame frame;
    // 全局变量，用于存储窗口对象
    private JTextField usernameField, searchField;
    // 存储文本框对象
    private JPasswordField passwordField;
    // 存储密码框对象
    private JTable userTable;
    // 表格
    private final UserDAO userDAO;
    private User loggedInUser = null;
    private UserTableModel userTableModel;

    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());


    public GameClient(UserDAO userDAO) {
        this.userDAO = userDAO;
        try {
            MaterialLookAndFeel material = new MaterialLookAndFeel(new MaterialOrientalFontsTheme());
            UIManager.setLookAndFeel(material);
        } catch (UnsupportedLookAndFeelException e) {
            logger.warning("Failed to initialize MaterialLookAndFeel: " + e.getMessage());
        }
        showLoginPage();
    }

    private Font getFallbackFont(boolean bold) {
        return getFallbackFont(bold, 18); // 默认字体大小为18
    }

    /**
     * 解决不同系统下字体显示不一致的问题
     *
     * @param bold 是否加粗
     * @param size 字体大小
     * @return 字体
     */
    private Font getFallbackFont(boolean bold, int size) {
        Font primaryFont = new Font("Microsoft YaHei", bold ? Font.BOLD : Font.PLAIN, size);
        Font fallbackFont = new Font("Arial", bold ? Font.BOLD : Font.PLAIN, size);
        HashMap<TextAttribute, Object> textAttributes = new HashMap<>();
        textAttributes.put(TextAttribute.FAMILY, primaryFont.getFamily());
        return fallbackFont.deriveFont(textAttributes);
    }

    /**
     * 显示登录页面
     */
    private void showLoginPage() {
        if (frame != null) {
            frame.dispose();
        }
        frame = new JFrame("登录");
        frame.setUndecorated(false); // 设置有边框，尝试做了一下无边框，结果不好看
        frame.setLocationRelativeTo(null); // 居中显示
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭窗口时退出程序
        frame.setSize(500, 300); // 初始窗口大小
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 添加边距

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 元素之间的间距
        gbc.fill = GridBagConstraints.BOTH;

        int labelFontSize = 18; // 调整字体大小
        int fieldFontSize = 16;

        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(getFallbackFont(true, labelFontSize));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        // 居中
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setFont(getFallbackFont(false, fieldFontSize));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0.8;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(getFallbackFont(true, labelFontSize)); // 自定义字体
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(getFallbackFont(false, fieldFontSize));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.8;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("登录");
        loginButton.setFont(getFallbackFont(false, fieldFontSize));
        loginButton.addActionListener(new LoginAction());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("注册");
        registerButton.setFont(getFallbackFont(false, fieldFontSize));
        registerButton.addActionListener(e -> showRegisterPage());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        panel.add(registerButton, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * 显示注册页面
     */
    private void showRegisterPage() {
        JFrame registerFrame = new JFrame("注册");
        registerFrame.setUndecorated(false);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setSize(600, 300); // 初始窗口大小加大
        registerFrame.setLocationRelativeTo(null); // 居中显示
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 元素之间的间距
        gbc.fill = GridBagConstraints.BOTH;

        int fontSize = 18;

        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(getFallbackFont(true, fontSize)); // 自定义字体
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        panel.add(usernameLabel, gbc);

        JTextField regUsernameField = new JTextField();
        regUsernameField.setFont(getFallbackFont(false, fontSize));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0.8;
        panel.add(regUsernameField, gbc);

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(getFallbackFont(true, fontSize));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.2;
        panel.add(passwordLabel, gbc);

        JPasswordField regPasswordField = new JPasswordField();
        regPasswordField.setFont(getFallbackFont(false, fontSize));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // 调整宽度为2
        gbc.weightx = 0.8;
        panel.add(regPasswordField, gbc);

        JButton registerButton = getjButton(regUsernameField, regPasswordField, registerFrame);
        registerButton.setFont(getFallbackFont(false, fontSize));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(registerButton, gbc);

        JButton backButton = new JButton("返回");
        backButton.setFont(getFallbackFont(false, fontSize));
        backButton.addActionListener(e -> registerFrame.dispose());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(backButton, gbc);

        registerFrame.add(panel);
        registerFrame.setVisible(true);
    }

    /**
     * 获取注册按钮，传递对象，注册按钮的监听事件
     *
     * @param regUsernameField 用户名输入框
     * @param regPasswordField 密码输入框
     * @param registerFrame    注册窗口
     * @return 注册按钮
     */
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

    /**
     * 显示用户管理页面
     */
    private void showUserManagementPage() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setTitle("游戏积分管理 - 登录为 " + loggedInUser.getUsername());

        JPanel panel = new JPanel(new BorderLayout(10, 10)); // 增加组件间的间隔
        int fontSize = 18;

        // 上部搜索栏
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5)); // 增加组件间的间隔
        searchField = new JTextField();
        searchField.setFont(getFallbackFont(false, fontSize));
        // 设置字体大小
        JButton searchButton = new JButton("搜索");
        searchButton.setFont(getFallbackFont(false, fontSize));
        searchButton.addActionListener(new SearchAction());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        panel.add(searchPanel, BorderLayout.NORTH);

        // 中部表格
        userTableModel = new UserTableModel();
        userTable = new JTable(userTableModel);
        userTable.setFont(getFallbackFont(false, fontSize));
        userTable.setRowHeight(25); // 设置行高，确保在高分辨率下的可读性
        userTable.getTableHeader().setFont(getFallbackFont(true, fontSize));

        userTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 检测双击
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
        editItem.setFont(getFallbackFont(false, fontSize));
        editItem.addActionListener(new EditAction());
        JMenuItem deleteItem = new JMenuItem("删除");
        deleteItem.setFont(getFallbackFont(false, fontSize));
        deleteItem.addActionListener(new DeleteAction());
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        userTable.setComponentPopupMenu(popupMenu);
        userTable.addMouseListener(new TableMouseListener(userTable));

        panel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        // 下部登出按钮
        JButton logoutButton = new JButton("登出");
        logoutButton.setFont(getFallbackFont(false, fontSize));
        logoutButton.addActionListener(new LogoutAction());

        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(new BoxLayout(logoutPanel, BoxLayout.PAGE_AXIS));
        logoutPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 添加一些空间
        logoutPanel.add(logoutButton);

        panel.add(logoutPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * 登录按钮监听器
     */
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

    /**
     * 搜索按钮监听器
     */
    private class SearchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchText = searchField.getText();
            refreshUserList(searchText);
        }
    }

    /**
     * 修改按钮监听器
     */
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

    /**
     * 登出按钮监听器
     */
    private class LogoutAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loggedInUser = null;
            UserDAO userDAO = new UserDAO();
            if (!userDAO.testConnection()) {
                JOptionPane.showMessageDialog(null, "无法连接到数据库，请检查数据库配置");
                System.exit(1);
            }
            frame.dispose();
            new GameClient(userDAO); // 创建新的 GameClient 实例
        }
    }

    /**
     * 删除按钮监听器
     */
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

    /**
     * 显示修改用户对话框
     *
     * @param user 用户对象
     */
    private void showEditDialog(User user) {
        JDialog editDialog = new JDialog(frame, "修改用户", true);
        editDialog.setSize(400, 300); // 调整窗口大小以适应更高分辨率
        editDialog.setLocationRelativeTo(frame); // 居中显示
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 设置内边距
        gbc.fill = GridBagConstraints.BOTH;
        int labelFontSize = 18;
        // 调整标签字体大小
        int fieldFontSize = 16;
        // 调整输入框字体大小
        // 用户名
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setFont(getFallbackFont(true, labelFontSize));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(user.getUsername());
        usernameField.setFont(getFallbackFont(false, fieldFontSize));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 0.7;
        panel.add(usernameField, gbc);

        // 密码
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(getFallbackFont(true, labelFontSize));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(user.getPassword());
        passwordField.setFont(getFallbackFont(false, fieldFontSize));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(passwordField, gbc);

        // 积分
        JLabel scoreLabel = new JLabel("积分:");
        scoreLabel.setFont(getFallbackFont(true, labelFontSize));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(scoreLabel, gbc);

        JTextField scoreField = new JTextField(String.valueOf(user.getScore()));
        scoreField.setFont(getFallbackFont(false, fieldFontSize));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(scoreField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton updateButton = new JButton("更新");
        updateButton.setFont(getFallbackFont(false, fieldFontSize));
        updateButton.addActionListener(e -> {
            try {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String scoreText = scoreField.getText();

                // 检查用户输入
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
        buttonPanel.add(updateButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.setFont(getFallbackFont(false, fieldFontSize));
        cancelButton.addActionListener(e -> editDialog.dispose()); // 取消按钮关闭对话框
        buttonPanel.add(cancelButton);

        // 添加按钮面板
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        panel.add(buttonPanel, gbc);

        editDialog.add(panel);
        editDialog.setVisible(true);
    }

    /**
     * 刷新用户列表
     *
     * @param searchText 搜索文本，如果为空则刷新所有用户，按照用户名搜索
     */
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
        // 忽略DPI设置
        System.setProperty("sun.java2d.dpiaware", "false");
        // 测试数据库链接
        UserDAO userDAO = new UserDAO();
        if (!userDAO.testConnection()) {
            JOptionPane.showMessageDialog(null, "无法连接到数据库，请检查数据库配置");
            System.exit(1);
        }
        // 启动客户端
        SwingUtilities.invokeLater(() -> new GameClient(userDAO));
    }
}


class TableMouseListener extends MouseAdapter {
    private final JTable table;

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

    /**
     * 显示右键菜单
     *
     * @param e 鼠标事件
     */
    private void showPopup(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint()); // 获取鼠标点击的行
        table.setRowSelectionInterval(row, row); // 选中该行
        JPopupMenu popup = table.getComponentPopupMenu(); // 获取右键菜单
        if (popup != null) {
            // 如果右键菜单不为空，显示右键菜单
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}


/**
 * 用户表格模型，用于显示用户列表
 */
class UserTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "用户名", "密码", "积分"};
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
        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getUsername();
            case 2:
                return user.getPassword();
            case 3:
                return user.getScore();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public User getUserAt(int rowIndex) {
        return users.get(rowIndex);
    }
}