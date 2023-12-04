package Feature;

import java.util.HashMap;

public class HashMapExp {
    public static void main(String[] args) {
        // 创建一个 HashMap 对象
        HashMap<String, Integer> map = new HashMap<>();

        // 添加元素到 HashMap
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        // 输出 HashMap
        System.out.println("Initial HashMap: " + map);

        // 访问 HashMap 中的元素
        int age = map.get("Alice");
        System.out.println("Alice's age: " + age);

        // 删除 HashMap 中的元素
        map.remove("Alice");
        System.out.println("HashMap after removal: " + map);

        // 检查 HashMap 是否包含某个键
        boolean hasBob = map.containsKey("Bob");
        System.out.println("Does map contain Bob? " + hasBob);

        // 检查 HashMap 是否包含某个值
        boolean hasAge30 = map.containsValue(30);
        System.out.println("Does map contain age 30? " + hasAge30);

        // 获取 HashMap 的大小
        int size = map.size();
        System.out.println("Size of map: " + size);
    }
}