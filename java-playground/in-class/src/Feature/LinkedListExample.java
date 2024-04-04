package Feature;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<Object> linkedList = new LinkedList<>();

        // 添加元素
        linkedList.add("Element 1");
        linkedList.add("Element 2");
        linkedList.add("Element 3");

        // 在指定位置添加元素
        linkedList.add(1, "Element 4");

        // 添加其他种类的元素
        linkedList.add(2);
        linkedList.add(3.1415926);
        linkedList.add(new ArrayList<>());


        // 移除元素
        linkedList.remove("Element 2");

        // 获取元素
        // 判断元素是否为 String 类型
        if (linkedList.get(0) instanceof String) {
            String element = (String) linkedList.get(0);
            System.out.println(element);
        }
        // 遍历元素
        for (Object s : linkedList) {
            System.out.println(s);
        }
    }
}