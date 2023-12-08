import java.util.Scanner;

class CircleClass {
    private double radius;

    public CircleClass(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}

class Cylinder {
    private double height;
    private CircleClass circle;

    public Cylinder(double radius, double height) {
        if (radius < 0 || height < 0) {
            throw new IllegalArgumentException("input error");
        }
        this.height = height;
        this.circle = new CircleClass(radius);
    }

    public double getVolume() {
        return circle.getArea() * height;
    }
}

public class Circle2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // System.out.print("请输入圆柱的底面半径和高：");

        try {
            double radius = scanner.nextDouble();
            double height = scanner.nextDouble();

            // 验证数据不为0
            if (radius == 0 || height == 0) {
                throw new IllegalArgumentException("input error");
            }

            Cylinder cylinder = new Cylinder(radius, height);
            double volume = cylinder.getVolume();
            System.out.printf("%.2f", volume);
        } catch (IllegalArgumentException e) {
            System.out.println("input error");
        }
        scanner.close();
    }
}