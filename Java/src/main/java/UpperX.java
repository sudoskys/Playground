import java.util.Scanner;

public class UpperX {

    public static String change(String s) {
        // String 是不可变的，所以需要使用 StringBuffer
        // 效率上，StringBuffer > StringBuilder > String
        // StringBuffer 线程安全，StringBuilder 线程不安全
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 != 0) {
                // 奇数位
                if (Character.isLetter(s.charAt(i))) {
                    sb.setCharAt(i, Character.toUpperCase(s.charAt(i)));
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");
        String s = "abc";
        assert change(s).equals("aBc"); // 避免使用 == 比较值
    }
}
