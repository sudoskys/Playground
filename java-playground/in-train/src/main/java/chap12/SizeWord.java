package chap12;

import java.util.*;

public class SizeWord {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        char[] arr = str.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : arr) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        // 获取键的集合并转换为列表
        List<Character> list = new ArrayList<>(map.keySet());
        // 对列表进行排序
        Collections.sort(list);
        // 遍历列表并打印结果
        System.out.print("{");
        for (Character c : list) {
            System.out.print(c + "=" + map.get(c));
            if (list.indexOf(c) != list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
}