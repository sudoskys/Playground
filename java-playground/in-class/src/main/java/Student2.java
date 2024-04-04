import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Student2 {
    private String sno;
    private String sname;
    private String sex;
    private int height;
    private Date brithDate;

    public Student2() {
    }

    public Student2(String sno, String sname, String sex, int height, String brithDate) {
        this.sno = sno;
        this.sname = sname;
        this.sex = sex;
        this.height = height;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.brithDate = sdf.parse(brithDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        if (Objects.equals(this.sex, "m")) {
            return "男";
        } else {
            return "女";
        }
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(String brithDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.brithDate = sdf.parse(brithDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Student[" + sno + "," + sname + "," + this.getSex() + "," + height + "," + sdf.format(brithDate) + "]";
    }

    public static void main(String[] arg) {
        /*
        测试用例1：

输入：20151001 张三 m 180 1980-01-01

输出：Student[20151001,张三,男,180,1980-01-01]
        * */
        Scanner scanner = new Scanner(System.in);
        String[] strArr = scanner.nextLine().split(" ");
        Student2 student2 = new Student2(strArr[0], strArr[1], strArr[2], Integer.parseInt(strArr[3]), strArr[4]);
        System.out.println(student2.toString());

    }

}