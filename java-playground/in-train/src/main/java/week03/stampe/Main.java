package week03.stampe;

import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int count1 = 5; // 3分邮票数量
        int count2 = 4; // 5分邮票数量
        TreeSet<Integer> set1 = new TreeSet<>(); // 存储邮资的升序队列
        TreeSet<Integer> set2 = new TreeSet<>(new Comparator<Integer>() { // 自定义邮资的降序队列
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // lambda 表达式实现降序队列

        TreeSet<Integer> set3 = new TreeSet<>((o1, o2) -> o2 - o1);

        // 双重循环实现邮票的各种组合
        for (int i = 0; i <= count1; i++) {
            for (int j = 0; j <= count2; j++) {
                set1.add(i * 3 + j * 5);
                set2.add(i * 3 + j * 5);
            }
        }
        // 删除 0
        set1.remove(0);
        set2.remove(0);

        // 输出升序队列和降序队列
        System.out.println(set1);
        System.out.println(set2);
    }
}