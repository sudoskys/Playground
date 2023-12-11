import java.util.Scanner;

public class StringOperations {
    // Method to count the occurrence of a character in a string
    public static int charCount(char c, String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    // Method to move the first m characters to the end of the string
    public static String moveStr(String str, int m) {
        return str.substring(m) + str.substring(0, m);
    }

    // Method to sort the string except the first and last character
    public static String sort(String str) {
        char[] chars = str.substring(1, str.length() - 1).toCharArray();
        java.util.Arrays.sort(chars);
        // 降序排列
        // java.util.Arrays.sort(chars, Collections.reverseOrder());
        return str.charAt(0) + new String(chars) + str.charAt(str.length() - 1);
    }

    // Method to remove trailing asterisks from the string
    public static String delStar(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == '*') {
            i--;
        }
        return s.substring(0, i + 1);
    }

    public static void main(String[] args) {
        // 输入数据
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char c = sc.next().charAt(0);
        int m = sc.nextInt();

        System.out.println(StringOperations.charCount(c, str));
        System.out.println(StringOperations.moveStr(str, m));
        System.out.println(StringOperations.sort(str));
        System.out.println(StringOperations.delStar(str));
    }
}

