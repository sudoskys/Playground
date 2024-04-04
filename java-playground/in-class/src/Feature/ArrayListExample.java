package Feature;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();

        // 添加元素
        arrayList.add("Element 1");
        arrayList.add("Element 2");
        arrayList.add("Element 3");

        // 在指定位置添加元素
        arrayList.add(1, "Element 4");

        // 移除元素
        arrayList.remove("Element 2");

        // 移除指定位置的元素
        arrayList.remove(1); // 移除索引为1的元素

        // 使用lambda表达式进行排序
        arrayList.sort(Comparator.naturalOrder());


        // 获取元素
        String element = arrayList.get(0);
index :
        // 遍历元素
        for (String s : arrayList) {
            System.out.println(s);
        }
    }
}