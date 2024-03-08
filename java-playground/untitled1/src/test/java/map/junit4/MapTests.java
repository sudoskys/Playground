package map.junit4;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class MapTests {
    // Hash Map 的基本操作
    @Test
    public void testHashmap() {
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

        // 更改Map中的值
        map.put("apple", 10);
        assertEquals(10, (int) map.get("apple"));
    }

    @Test
    public void testTreeMap() {
        TreeMap<String, Integer> map = new TreeMap<>();
        // 填充数据
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);
        // 遍历
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
        // 匿名函数遍历
        map.forEach((key, value) -> System.out.println(key + " " + value));
        // 删除
        map.remove("banana");
        // 循环并修改值为3的键
        for (String key : map.keySet()) {
            if (map.get(key) == 3) {
                map.put(key, 30);
            }
        }
        // 校验数据
        assertEquals(30, (int) map.get("cherry"));
    }
}
