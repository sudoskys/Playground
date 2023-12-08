import java.util.Scanner;

// 计算椭圆的面积
public class Tuoyuan {
    double a;
    double b;

    public Tuoyuan(double a, double b) {
        this.a = a;
        this.b = b;
    }

    // 该方法实现计算椭圆面积的功能，将计算结果返回，面积 = π * a * b
    public double area() {
        // 校验参数是否合法，否则报错
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("ERROR");
        }
        return Math.PI * a * b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入的参数
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        // 有参方法实例化对象
        Tuoyuan t = new Tuoyuan(a, b);
        double area;
        // 调用 area 方法计算椭圆面积
        try {
            area = t.area();
        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
            return;
        }
        System.out.print(String.format("%.2f", area));

        // 四舍五入格式化不换行输出椭圆面积，输出格式：椭圆的面积为xx
    }
}
