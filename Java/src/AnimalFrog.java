
import java.util.Scanner;

abstract class BaseAnimal {
    protected boolean mammal = true; // 哺乳动物
    protected boolean carnivorous = true; //食肉动物
    public static final int scared = 0; //情绪不好
    public static final int comforted = 1; //情绪好
    protected int mood = comforted;

    public boolean ismammal() {
        return (mammal);
    }

    public boolean iscarnivorous() {
        return (carnivorous);
    }

    abstract String SayHello();

    abstract String SayHello(int moodval);

    public void setMood(int newValue) {
        mood = newValue;
    }

    public int getMood() {
        return (mood);
    }

    ;
}

interface LandAnimal {
    public int getNumberOfLegs();//返回有多少条腿
}

interface WaterAnimal {
    public boolean isGillFlag();//返回是否有腮

    public boolean isLaysEggs();//返回是否产卵
}

class BaseDog extends BaseAnimal implements LandAnimal {
    private int numberOfLegs = 4;

    public String SayHello() {
        return "摇摇尾巴";
    }

    ;

    public String SayHello(int moodval) {
        this.setMood(moodval);
        switch (mood) {
            case scared:
                return ("呜呜叫");
            case comforted:
                return ("汪汪叫");
        }
        return ("摇摇尾巴");
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }
}

class BaseCat extends BaseAnimal implements LandAnimal {
    private int numberOfLegs = 4;

    public String SayHello() {
        return "喵喵叫";
    }

    ;

    public String SayHello(int moodval) {
        this.setMood(moodval);
        switch (mood) {
            case scared:
                return ("嘶嘶");
            case comforted:
                return ("咕噜咕噜");
        }
        return ("喵喵叫");
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }
}

class BaseFrog extends BaseAnimal implements LandAnimal, WaterAnimal {
    private int numberOfLegs = 4;
    private boolean gillFlag = true;

    public BaseFrog() {
        mammal = false;
        carnivorous = false;
    }

    public String SayHello() {
        return "呱呱";
    }

    ;

    public String SayHello(int moodval) {
        this.setMood(moodval);
        switch (mood) {
            case scared:
                return ("扑通一声跳入水中");
            case comforted:
                return ("呱呱呱");
        }
        return ("呱呱");
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }

    public boolean isGillFlag() {
        return gillFlag;
    }

    public boolean isLaysEggs() {
        return gillFlag;
    }
}

public class AnimalFrog {
    public static void main(String args[]) {
        BaseDog dog = new BaseDog();
        BaseCat cat = new BaseCat();
        BaseFrog frog = new BaseFrog();
        System.out.println("          ~欢迎光临索迪动物园~     ");
        for (; ; ) {
            System.out.println("请输入动物名称：dog，cat或者frog ，退出exit！");
            String name = new String();
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            name = sc.next();
            switch (name) {
                case "dog":
                    System.out.println("该动物是陆生动物，它有" + dog.getNumberOfLegs() + "条腿");
                    System.out.println("狗通常情况下，跟人的打招呼方式为：" + dog.SayHello());
                    break;
                case "cat":
                    System.out.println("该动物是陆生动物，它有" + cat.getNumberOfLegs() + "条腿");
                    System.out.println("猫通常情况下，跟人的打招呼方式为：" + cat.SayHello());
                    break;
                case "frog":
                    if (frog.isLaysEggs() && frog.isGillFlag())
                        System.out.println("该动物是陆生动物，它有" + frog.getNumberOfLegs() + "条腿。" + "有腮" + "," + "产卵");
                    System.out.println("青蛙通常情况下，跟人的打招呼方式为：" + frog.SayHello());
                    break;
                case "exit":
                    System.out.println("再见！");
                    System.exit(0);
                default:
                    System.out.println("输入有误 ！请重新输入！");
            }
            System.out.println("请输入动物的心情：1-心情好，2-心情不好");
            int i;
            @SuppressWarnings("resource")
            Scanner sc1 = new Scanner(System.in);
            i = sc1.nextInt();
            switch (i) {
                case 1:
                    System.out.println("它心情是：心情好");
                    break;
                case 2:
                    System.out.println("它心情是：心情不好");
                    break;
                default:
                    System.out.println("输入有误 ！请重新输入！");
            }
            switch (name) {
                case "dog":
                    if (i == 1) {
                        System.out.println("狗心情好：" + dog.SayHello(Animal.comforted));
                    } else if (i == 2) {
                        System.out.println("狗心情不好：" + dog.SayHello(Animal.scared));
                    } else {
                        System.out.println("输入错误！");
                    }
                    break;
                case "cat":
                    if (i == 1) {
                        System.out.println("猫心情好：" + cat.SayHello(Animal.comforted));
                    } else if (i == 2) {
                        System.out.println("猫心情不好：" + cat.SayHello(Animal.scared));
                    } else {
                        System.out.println("输入错误！");
                    }
                    break;
                case "frog":
                    if (i == 1) {
                        System.out.println("青蛙心情好：" + frog.SayHello(Animal.comforted));
                    } else if (i == 2) {
                        System.out.println("青蛙心情不好：" + frog.SayHello(Animal.scared));
                    } else {
                        System.out.println("输入错误！");
                    }
                    break;
                default:
                    System.out.println("输入有误 ！请重新输入！");
                    break;
            }
        }

    }
}