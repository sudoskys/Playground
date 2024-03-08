package map.junit4;

import org.junit.Test;

import java.util.Scanner;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * TreeMapTest
 * 通过键盘输入一个字符串，按字母顺序打印出每个字符及其出现的次数。
 * 编程提示：
 * （1）用TreeMap<Character,Integer>实现。
 * （2）循环遍历该字符串中的每一个字符，如果TreeMap中已经存储该字符，则出现次数加1，否则存储该字符，出现次数为1。
 * (3)  TreeMap已经覆盖toString()方法，直接用System.out.println打印即可输出Tree Map的内容。
 * 测试用例：
 * 输入：dfsaffasg
 * 输出：{a=2, d=1, f=3, g=1, s=2}
 */
public class TreeMapTest {
    @Test
    public void testTreeMap() {
        assertEquals("{a=2, d=1, f=3, g=1, s=2}", getTreeMap());
    }

    private String getTreeMap() {
        TreeMap<Character, Integer> ts = new TreeMap<>();
        Scanner sc = new Scanner("dfsaffasg");
        String task = sc.nextLine();
        for (int i = 0; i < task.length(); i++) {
            char c = task.charAt(i);
            if (ts.containsKey(c)) {
                ts.put(c, ts.get(c) + 1);
            } else {
                ts.put(c, 1);
            }
        }
        System.out.println(ts);
        return ts.toString();
    }

    public static void main(String[] args) {
        TreeMap<Character, Integer> ts = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        for (int i = 0; i < task.length(); i++) {
            char c = task.charAt(i);
            if (ts.containsKey(c)) {
                ts.put(c, ts.get(c) + 1);
            } else {
                ts.put(c, 1);
            }
        }
        System.out.println(ts);
    }
}
