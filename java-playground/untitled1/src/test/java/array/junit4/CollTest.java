package array.junit4;/*
* 任务：接收给定的一行字符串，实现如下需求：
1.通过空格（一个）切割字符串；
2.创建任意一种类型的集合；
3.将切割后的字符串元素添加进集合；
4.打印集合。
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CollTest {
    public static void main(String[] args) {
        // 请在Begin-End间编写代码
        /********* Begin **********/
        // 第一步：接收给定的一行字符串
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        // 第二步：切割字符串
        String[] split = task.trim().split(" ");
        // 筛查空
        if (split.length == 0) {
            System.out.println("输入为空");
            return;
        }
        // 第三步：创建集合
        // 第四步：往集合中添加元素
        ArrayList<String> lists = new ArrayList<>(Arrays.asList(split));
        // 第五步：删除第一个元素和最后一个元素
        lists.remove(0);
        lists.remove(lists.size() - 1);
        // 第六步：往集合中添加hello和educoder
        lists.add("hello");
        lists.add("educoder");
        // 第七步：修改集合中第三个元素为list
        lists.set(2, "list");
        // 第五步：打印集合元素
        System.out.println(lists);
        /********** End **********/
    }
}
