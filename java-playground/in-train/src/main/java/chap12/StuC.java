package chap12;

import java.util.Map;
import java.util.Scanner;

public class StuC {
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
                System.out.println("输入的值不是数字");
                return;
            }
            map.put(key, Integer.parseInt(kv[1].trim()));
        }
        System.out.println(map);
    }
}
