/**
 * 任务：将键盘输入的三个学生的信息存入 Student 对象中，最后将这些学生信息按预期输出格式打印输出。
 * 类名为：Student
 */

import java.util.Arrays;
import java.util.Scanner;

public class Student {
    private String name;  // 学生的姓名
    private String num;  // 学生的学号信息
    private double grades;  // 学生的成绩

    // 有参构造方法
    public Student(String name, String num, double grades) {
        this.name = name;
        this.num = num;
        this.grades = grades;
    }

    // 获取和设置学生的属性信息
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    public static Student input_from_keyboard() {
        Scanner scan = new Scanner(System.in);
        String next = scan.nextLine();
        String[] info = next.split(",");
        return new Student(info[0], info[1], Double.parseDouble(info[2]));
    }

    public static void main(String[] args) {
        // 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码
        /********* Begin **********/

        // 创建可以存放三个对象的对象数组
        Student[] student = new Student[3];
        // 获取键盘输入的学生信息，将数组中的对象进行实例化


        Scanner scan = new Scanner(System.in);
        for (String next : new String[]{"a"}) {
            int n = 0;
            String[] info = next.split(",");
            assert info.length == 3 : "错误";
            student[n] = new Student(info[0], info[1], Double.parseDouble(info[2]));
            ++n;
            // student[i] = Student.input_from_keyboard();
        }
        // 打印输出每个学生的信息
        for (Student student_item : student) {
            System.out.printf("姓名：%s    学号：%s    成绩：%.2f\n", student_item.name, student_item.num, student_item.grades);
        }
        /********* End **********/
    }
}