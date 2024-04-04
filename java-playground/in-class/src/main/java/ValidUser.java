import java.util.Scanner;

public class ValidUser {
    private void checkName(String name) {
        if (name == null || name.length() < 6 || name.length() > 10) {
            throw new IllegalArgumentException("用户名必须在6-10个字符之间");
        }
        for (char c : name.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '_') {
                throw new IllegalArgumentException("用户名只能包含字母或数字或下划线");
            }
        }
        // 不能以数字开头
        if (Character.isDigit(name.charAt(0))) {
            throw new IllegalArgumentException("用户名不能以数字开头");
        }
    }

    private void checkPassword(String password) {
        if (password == null || password.length() < 6 || password.length() > 20) {
            throw new IllegalArgumentException("口令必须在6-20个字符之间");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ValidUser validUser = new ValidUser();
        //System.out.println("请输入用户名：");
        String name = sc.next();
        //System.out.println("请输入密码：");
        String password = sc.next();
        try {
            validUser.checkName(name);
            validUser.checkPassword(password);
            System.out.println("用户名和密码正确");
        } catch (IllegalArgumentException e) {
            // System.out.println("注册失败，原因是：" + e.getMessage());
            System.out.println("用户名或密码错误");
        }
    }
}
