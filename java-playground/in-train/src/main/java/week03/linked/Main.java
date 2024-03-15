package week03.linked;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner nc = new Scanner(System.in);
        String input = nc.nextLine();
        System.out.println(checkBrackets(input));
        TreeSet<Integer> set1 = new TreeSet<>();
        HashSet<Integer> set2 = new HashSet<>();

    }

    public static String checkBrackets(String input) {
        LinkedList<Character> stack = new LinkedList<>();
        int count = 0;
        for (char c : input.toCharArray()) {
            if (c == '[') {
                stack.push(c);
            } else if (c == ']') {
                if (stack.isEmpty()) {
                    return "期望[";
                } else {
                    stack.pop();
                    count++;
                }
            }
        }
        if (!stack.isEmpty()) {
            return "期望]";
        } else {
            return "匹配\n" + count;
        }
    }
}
