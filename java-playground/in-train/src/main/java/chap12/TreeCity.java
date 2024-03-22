package chap12;

import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;

public class TreeCity {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //接收 key:value, key:value, key:value, key:value
        String str = input.nextLine();
        String[] arr = str.split("，");
        TreeMap<Integer, String> map = new TreeMap<>();
        for (String s : arr) {
            String[] kv = s.split("：");
            String value = kv[1].trim();
            // 校验是否可以被转换为数字
            if (!kv[0].trim().matches("\\d+")) {
                throw new IllegalArgumentException("输入的值不是数字");
            }
            Integer key = Integer.parseInt(kv[0].trim());
            map.put(key, value);
        }
        System.out.println(map.descendingKeySet());
        System.out.printf("城市编码最大的元素：%s。城市编码最小的元素：%s%n", map.lastEntry(), map.firstEntry());
        // 字符串模板
        String template = String.format("删除的元素是：%s", map.pollFirstEntry());
        System.out.println(template);
        System.out.println(map);
        // 743：湖南，744：湖北，321：北京
    }
}
