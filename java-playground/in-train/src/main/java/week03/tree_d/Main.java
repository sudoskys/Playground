package week03.tree_d;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<String> setA = new TreeSet<>(), setB = new TreeSet<>(), tempA, tempB;

        // 输入集合A的元素
        String[] elementsA = scanner.nextLine().split(" ");
        Collections.addAll(setA, elementsA);

        // 输入集合B的元素
        String[] elementsB = scanner.nextLine().split(" ");
        Collections.addAll(setB, elementsB);

        // 执行操作前对集合进行备份
        try {
            tempA = (TreeSet<String>) setA.clone();
            tempB = (TreeSet<String>) setB.clone();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // 交集计算
        tempA.retainAll(setB);
        for (String item : tempA) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 并集计算
        tempB.addAll(setA);
        for (String item : tempB) {
            System.out.print(item + " ");
        }
        System.out.println();

        // 差集计算
        setA.removeAll(setB);
        for (String item : setA) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}