package map_d;

import java.util.HashMap;


public class Main {
    // Hash Map 的基本操作
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        System.out.println(map.get("apple"));
        System.out.println(map.get("banana"));
        System.out.println(map.get("cherry"));

        // 遍历
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
        // 删除
        map.remove("banana");

        // 使⽤ containsKey 判断是否存在某个键
        System.out.println(map.containsKey("banana"));

        // 使用迭代器遍历
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        // 使用 forEach 遍历
        map.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
