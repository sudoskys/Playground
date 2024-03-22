package chap12;

import java.util.Map;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //接收 key:value, key:value, key:value, key:value
        String str = input.nextLine();

        String[] arr = str.split("，");
        Map<String, Integer> map = new java.util.HashMap<>();
        for (String s : arr) {
            String[] kv = s.split("：");
            String key = kv[0].trim();
            // 校验是否可以被转换为数字
            if (!kv[1].trim().matches("\\d+")) {
                throw new IllegalArgumentException("输入的值不是数字");
            }
            map.put(key, Integer.parseInt(kv[1].trim()));
        }
        map.put("lamb", 50);
        // 输出菜名
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            //System.out.println(entry.getKey());
        }
        System.out.println(map.keySet());
        // 删除
        map.remove("红烧鱼");
        map.remove("小炒牛肉");
        // 长度
        System.out.println(map.size());
        System.out.println(map);
    }
}
