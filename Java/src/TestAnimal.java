/*
 * 多态的样例
 * */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class TestAnimal {
    public static void main(String[] args) {
        Animal a = new Cat();
        a.spark();
        Animal b = new Dogs();
        b.spark();

        // 向下转型
        Cat c = (Cat) a;
    }

    // 多态的实现
    public static void classfiy(Animal a) {
        // 判定a是否是Cat的实例
        if (a instanceof Cat) {
            Cat c = (Cat) a;
            c.spark();
        } else if (a instanceof Dogs) {
            Dogs d = (Dogs) a;
            d.spark();
        }
        ArrayList<Animal> ist = new ArrayList<Animal>();
        ist.add(new Cat());
        ist.add(new Dogs());
        // sort
        Animal[] animals = new Animal[0];
        ist.toArray(animals);
        for (Animal animal : ist) {
            animal.spark();
        }
    }
}


abstract class Animal {
    void spark() {
        System.out.println("叫");
    }
}

class Cat extends Animal {
    public void spark() {
        System.out.println("喵");
    }
}

class Dogs extends Animal {
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
