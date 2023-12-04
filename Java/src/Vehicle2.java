import java.util.Scanner;

class Vehicle {
    protected int wheels;
    protected double weight;

    public Vehicle() {
    }

    public Vehicle(int wheels, double weight) {
        this.wheels = wheels;
        this.weight = weight;
    }

    public String toString() {
        return "Vehicle[wheels:" + wheels + ",weight:" + weight + "]";
    }
}

class Car extends Vehicle {
    protected int loader;

    public Car() {
    }

    public Car(int wheels, double weight, int loader) {
        super(wheels, weight);
        this.loader = loader;
    }

    public String toString() {
        return "Car[wheels:" + wheels + ",weight:" + weight + ",loader:" + loader + "]";
    }
}

class Truck extends Car {
    protected double payload;

    public Truck() {
    }

    public Truck(int wheels, double weight, int loader, double payload) {
        super(wheels, weight, loader);
        this.payload = payload;
    }

    public String toString() {
        return "Truck[wheels:" + wheels + ",weight:" + weight + ",loader:" + loader + ",payload:" + payload + "]";
    }
}

public class Vehicle2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wheels1 = scanner.nextInt();
        double weight1 = scanner.nextDouble();
        Vehicle vehicle = new Vehicle(wheels1, weight1);
        System.out.println(vehicle.toString());

        int wheels2 = scanner.nextInt();
        double weight2 = scanner.nextDouble();
        int loader2 = scanner.nextInt();
        Car car = new Car(wheels2, weight2, loader2);
        System.out.println(car);

        int wheels3 = scanner.nextInt();
        double weight3 = scanner.nextDouble();
        int loader3 = scanner.nextInt();
        double payload3 = scanner.nextDouble();
        Truck truck = new Truck(wheels3, weight3, loader3, payload3);
        System.out.println(truck.toString());
    }
}