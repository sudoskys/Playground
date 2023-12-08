import java.util.Scanner;

public class Complex {
    int real;
    int image1;
    int read2;
    int image2;

    public Complex(int real, int image1, int read2, int image2) {
        this.real = real;
        this.image1 = image1;
        this.read2 = read2;
        this.image2 = image2;
    }

    public String add() {
        if ((real + read2) == 0 && (image1 + image2) == 0) {
            return "0";
        }
        if ((real + read2) == 0) {
            return (image1 + image2) + "i";
        }
        if ((image1 + image2) == 0) {
            return String.valueOf(real + read2);
        }
        return (real + read2) + "+" + (image1 + image2) + "i";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int real = scanner.nextInt();
        int image1 = scanner.nextInt();
        int read2 = scanner.nextInt();
        int image2 = scanner.nextInt();
        Complex c1 = new Complex(real, image1, read2, image2);
        System.out.println(c1.add());
    }
}
