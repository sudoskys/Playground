package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean isStartWithWang(String name) {
        if (name.startsWith("王")) {
            System.out.println(name + "被移除了...");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner nc = new Scanner(System.in);
        String st = nc.nextLine();
        String[] student = st.split(" ");

        ArrayList<String> map_s = new ArrayList<>(Arrays.asList(student));
        // 删除王姓
        map_s.removeIf(Main::isStartWithWang);
        // 打印剩余
        for (String nt : map_s) {
            System.out.println(nt);
        }
    }
}