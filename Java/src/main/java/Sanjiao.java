import java.util.Scanner;

public class Sanjiao {
    double a;
    double b;
    double c;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        // 计算是否构成三角形
        if (a + b > c && a + c > b && b + c > a) {
            System.out.print("这三条边可以构成三角形");
        } else {
            System.out.print("这三条边不能构成三角形");
        }
    }
}
