package linklist.junit4;


import java.util.LinkedList;
import java.util.Scanner;

public class CollTest {

    public static void main(String[] args) {
        // 请在Begin-End间编写代码

        // 接收给定的字符串
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        // 创建集合
        LinkedList<Character> list = new LinkedList<>();
        // 利用 LinkedList 特有 add First 方法将字符串中每个字符反转添加进集合
        for (int i = 0; i < str.length(); i++) {
            list.addFirst(str.charAt(i));
        }
        // 将集合中的元素拼接成一个字符串输出
        for (Character c : list) {
            System.out.print(c);
        }

    }
}

