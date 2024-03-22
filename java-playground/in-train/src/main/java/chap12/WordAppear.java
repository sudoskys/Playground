package chap12;

import java.util.Map;
import java.util.Scanner;

public class WordAppear {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //接收 key:value, key:value, key:value, key:value
        String str = input.nextLine();
        String[] arr = str.split(" ");
        Map<String, Integer> map = new java.util.HashMap<>();
        for (String s : arr) {
            String key = s.trim();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}
