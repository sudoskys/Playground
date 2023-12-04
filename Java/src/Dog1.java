/**
 * 任务：使用对象数组的方式创建 3 个 Dog 对象
 * 类名为：Dog
 * 该类为 Dog 的基本属性
 */
public class Dog1 {
    private String name;     // 小狗名称
    private String type;     // 小狗品种
    private int age;     // 小狗年龄
    private String hobby;    //小狗爱好

    public Dog1() {

    }

    public Dog1(String name, String type, int age, String hobby) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.hobby = hobby;
    }

    // 获取Dog姓名
    public String getName() {
        return name;
    }

    // 设置Dog姓名
    public void setName(String name) {
        this.name = name;
    }

    // 获取Dog种类
    public String getType() {
        return type;
    }

    // 设置Dog种类
    public void setType(String type) {
        this.type = type;
    }

    // 获取Dog年龄
    public int getAge() {
        return age;
    }

    // 设置Dog年龄
    public void setAge(int age) {
        this.age = age;
    }

    // 获取爱好
    public String getHobby() {
        return hobby;
    }

    // 设置爱好
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    // Dog的详细信息
    public void info() {
        System.out.printf("小狗名称：%s\n品种：%s\n小狗年龄：%d\n小狗爱好：%s\n", name, type, age, hobby);
    }


    public static void main(String[] args) {
        Dog1 d1 = new Dog1("Tom", "哈士奇", 2, "拆家");
        Dog1 d2 = new Dog1("jerry", "中华田园犬", 3, "护家");
        Dog1 d3 = new Dog1("旺财", "柯基", 2, "吃喝玩");
        // 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码
        // ********** Begin **********/
        // 将三个狗的对象放进对象数组中，并依次调用该对象的info方法
        Dog1[] dog;
        dog = new Dog1[]{d1, d2, d3};
        for (Dog1 dog_item : dog) {
            dog_item.info();
        }


        // ********** End **********/
    }
}
