/*
 * 多态的样例
 * */

import java.util.ArrayList;

public class TestAnimal {
    public static void main(String[] args) {
        Animal1 a = new Cat1();
        a.spark();
        Animal1 b = new Dogs();
        b.spark();

        // 向下转型
        Cat1 c = (Cat1) a;
    }

    // 多态的实现
    public static void classfiy(Animal1 a) {
        // 判定a是否是Cat的实例
        if (a instanceof Cat1) {
            Cat1 c = (Cat1) a;
            c.spark();
        } else if (a instanceof Dogs) {
            Dogs d = (Dogs) a;
            d.spark();
        }
        ArrayList<Animal1> ist = new ArrayList<Animal1>();
        ist.add(new Cat1());
        ist.add(new Dogs());
        // sort
        Animal1[] animals = new Animal1[0];
        ist.toArray(animals);
        for (Animal1 animal : ist) {
            animal.spark();
        }
    }
}


abstract class Animal1 {
    void spark() {
        System.out.println("叫");
    }
}

class Cat1 extends Animal1 {
    public void spark() {
        System.out.println("喵");
    }
}

class Dogs extends Animal1 {
    public void spark() {
        System.out.println("汪");
        System.out.print("父类调用:");
        super.spark();
    }

    private void test() {
        // 只能在本类中使用
        System.out.println("test");
    }

    protected void test2() {
        // 只能在本类和子类中使用
        System.out.println("test2");
    }
}
