// 定义静态方法 add,参数数量可变，将参数相加后返回int

import java.util.Scanner;

public class Kwargs {

    public static int add(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    public static void main(String[] args) {
        // 输入不定数量的参数
        Scanner scanner = new Scanner(System.in);
        String[] strArr = scanner.nextLine().split(" ");
        int[] arr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }
        // 调用 add 方法
        System.out.println(add(arr));
    }
}
