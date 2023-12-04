package Object;

public class Dog {
    private String name;
    private String breed;
    private int age;
    private String hobby;

    // 无参构造方法
    public Dog() {

    }

    // 全参构造方法
    public Dog(String name, String breed, int age, String hobby) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.hobby = hobby;
    }

    // Getter和Setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public static void main(String[] args) {
        Dog[] dogs = new Dog[3];

        dogs[0] = new Dog("Tom", "哈士奇", 2, "拆家");
        dogs[1] = new Dog("jerry", "中华田园犬", 3, "护家");
        dogs[2] = new Dog("旺财", "柯基", 2, "吃喝玩");

        for (Dog dog : dogs) {
            System.out.println("小狗名称：" + dog.getName());
            System.out.println("品种：" + dog.getBreed());
            System.out.println("小狗年龄：" + dog.getAge());
            System.out.println("小狗爱好：" + dog.getHobby());
            // System.out.println();
        }
    }
}