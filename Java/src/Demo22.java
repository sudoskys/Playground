import jdk.nashorn.internal.runtime.logging.Logger;

abstract class A {
    private int x;

    @Logger
    public A(int x) {
        this.x = x;
    }

    abstract void show();

    public int getX() {
        return x;
    }

    void print() {
        System.out.println("hello");
    }

}

interface B {
    int final_x = 10;

    void show();


    public default void show1() {

    }

    public static void main(String[] args) {
        A a = new A(10) {
            @Override
            void show() {
                System.out.println("hello");
            }
        };
        a.show();
        a.print();
    }

    void print();
}

public class Demo22 implements B {
    @Override
    public void show() {
        System.out.println("hello");
    }

    @Override
    public void show1() {

    }

    @Override
    public void print() {
        System.out.println("hello");
    }
}

