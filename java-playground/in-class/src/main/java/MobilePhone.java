import java.util.Scanner;

public class MobilePhone {
    private String brand;
    private String type;
    private String os;
    private double price;
    private int memory;

    public MobilePhone() {
    }

    public MobilePhone(String brand, String type, String os, double price, int memory) {
        this.brand = brand;
        this.type = type;
        this.os = os;
        this.price = price;
        this.memory = memory;
    }

    public void about() {
        System.out.println("品牌：" + brand);
        System.out.println("型号：" + type);
        System.out.println("操作系统：" + os);
        System.out.println("价格：" + price);
        System.out.println("内存：" + memory);
    }

    public void call(int number) {
        if (number == 1) {
            System.out.println("正在给爸爸打电话");
        } else if (number == 2) {
            System.out.println("正在给妈妈打电话");
        } else if (number == 3) {
            System.out.println("正在给姐姐打电话");
        } else {
            System.out.println("你所拨打的电话为空号");
        }
    }

    public void play(String song) {
        System.out.println("正在播放" + song);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        Input:小米 小米9 Android9 2599.0 8 2 浮夸
        */
        String brand = scanner.next();
        String type = scanner.next();
        String os = scanner.next();

        double price = scanner.nextDouble();
        int memory = scanner.nextInt();
        int number = scanner.nextInt();
        String song = scanner.next();
        MobilePhone mobilePhone = new MobilePhone(brand, type, os, price, memory);
        mobilePhone.about();
        mobilePhone.call(number);
        mobilePhone.play(song);

    }
}