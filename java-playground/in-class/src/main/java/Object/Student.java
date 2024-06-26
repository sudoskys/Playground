package Object;
/**
 * 任务：定义一个完整的学生类，该类定义了学生的基本信息。
 * 类名为：Student
 */

// 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码

/********** Begin **********/

// 创建一个名为 Student 的公开类

public class Student {
    // 定义学生的两个属性：姓名（name String）和年龄（age int）
    private String name;
    private int age;

    // 获取学生年龄
    public int getAge() {
        return age;
    }

    // 设置学生的年龄，将形参的值赋值给成员变量
    public void setAge(int age) {
        this.age = age;
    }

    // 获取学生姓名
    public String getName() {
        return name;
    }

    // 设置学生姓名，将形参的值赋值给成员变量
    public void setName(String name) {
        this.name = name;
    }

    // 该方法实现输出学生信息的功能。 输出格式：学生姓名：xx，年龄：xx
    public void info() {
        System.out.println("学生姓名：" + name + "，年龄：" + age);

    }
}

/********** End **********/
