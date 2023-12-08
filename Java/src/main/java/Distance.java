
// 已知AB点坐标，求AB之间的距离

import java.util.Scanner;

public class Distance {


    private static double distance(double x1, double y1, double x2, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        return Math.sqrt(x * x + y * y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        // 保留6位小数
        System.out.printf("%.6f", distance(x1, y1, x2, y2));
    }
}
