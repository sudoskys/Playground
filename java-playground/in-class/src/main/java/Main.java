import java.util.Scanner;

abstract class Animal {
    protected int mood = 1; // 1-心情好，2-心情不好

    abstract String SayHello(); // 重载

    abstract String SayHello(int moodVal);  // 重载
}

interface LandAnimal {
    int getNumberOfLegs(); // 获得腿的数量
}

interface WaterAnimal {
    boolean isGillFlag(); // 是否有鳃

    boolean isLaysEggs(); // 是否产卵
}

class Dog extends Animal implements LandAnimal {
    public String SayHello() {
        return "我是狗，你是谁";
    }

    public String SayHello(int moodVal) {
        return moodVal == 1 ? "我狗子很高兴" : "我狗子很生气";
    }

    public int getNumberOfLegs() {
        return 4;
    }
}

class Cat extends Animal implements LandAnimal {
    public String SayHello() {
        return "你是猫";
    }

    public String SayHello(int moodVal) {
        return moodVal == 1 ? "我猫桑很舒服" : "我猫桑好害怕";
    }

    public int getNumberOfLegs() {
        return 4;
    }
}

class Frog extends Animal implements LandAnimal, WaterAnimal {
    public String SayHello() {
        return "为什么你是青蛙";
    }

    /**
     * 重载
     *
     * @param moodVal 心情
     * @return String 说话:心情好-呱呱呱，心情不好-扑通一声跳入水中
     */
    public String SayHello(int moodVal) {
        return moodVal == 1 ? "青蛙心情好，呱呱呱" : "青蛙心情不好，直接给你一个大嘴巴子，扑通一声跳入水中";
    }

    public int getNumberOfLegs() {
        return 4;
    }

    public boolean isGillFlag() {
        return true;
    }

    /**
     * 产卵
     *
     * @return boolean
     */
    public boolean isLaysEggs() {
        return true;
    }
}

public class Main {
    public static void printClassInfo(Class<?> clazz) {
        // 打印类名
        System.out.println("Class: " + clazz.getName());

        // 打印父类
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            System.out.println("Superclass: " + superclass.getName());
        }

        // 打印实现的接口
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.print("Interfaces: ");
        for (Class<?> iface : interfaces) {
            System.out.print(iface.getName() + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        Frog frog = new Frog();
        Scanner sc = new Scanner(System.in);
        System.out.println("我是丁真珍珠，欢迎来到动物世界！");
        while (true) {
            System.out.println("\033[30;4m" + "输入想要变成的动物：dog，cat，frog，雪豹" + "\033[0m");
            String name = sc.next();
            Animal animal = null;
            switch (name) {
                case "dog":
                    animal = dog;
                    break;
                case "cat":
                    animal = cat;
                    break;
                case "frog":
                    animal = frog;
                    break;
                case "雪豹":
                    System.out.println("雪豹是什么？");
                    System.out.println("雪豹是我们的朋友，请你不要随便输入！");
                    continue;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("输入有误 ！请重新输入！");
                    continue;
            }
            System.out.println("该动物是：" + name);
            System.out.println("该动物有" + ((LandAnimal) animal).getNumberOfLegs() + "条腿");
            System.out.println("该动物" + (animal instanceof WaterAnimal ? "有" : "没有") + "鳃");
            Main.printClassInfo(animal.getClass());
            System.out.println("通常情况下，跟人的打招呼方式为：" + animal.SayHello());
            System.out.println("请输入动物的心情：1-心情好，2-心情不好");
            // 判定是否下一位为int类型
            while (!sc.hasNextInt()) {
                System.out.println("输入有误 ！恁是不是傻 ！恁输入的是：" + sc.next());
                System.out.println("请恁输入动物的心情：1-心情好，2-心情不好，而不是：" + sc.next() + "");
                sc.next();
            }
            int mood = sc.nextInt();
            System.out.println("它心情是：" + (mood == 1 ? "心情好" : "心情不好"));
            System.out.println("心情" + (mood == 1 ? "好" : "不好") + "时：" + animal.SayHello(mood));
        }
    }
}