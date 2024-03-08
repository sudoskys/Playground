package array.junit4;
// 第二步：接收给定的一行字符串


// 第三步：切割字符串


// 第四步：创建集合


// 第五步：把学生添加进集合


// 第六步：删除王姓开头的学生


// 第七步：打印剩余学生名

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class StudentTest {
    @Test
    public void testProcessInputSting() {
        ArrayList<String> map_d = processInputSting("王小明 王小丽 小李 小红");
        assertEquals(2, map_d.size());
    }

    public static boolean isStartWithWang(String name) {
        if (name.startsWith("王")) {
            System.out.println(name + "被移除了...");
            return true;
        }
        return false;
    }

    public ArrayList<String> processInputSting(String st) {
        // 切割字符串
        String[] student = st.split(" ");
        ArrayList<String> map_s = new ArrayList<>();
        if (student.length == 0) {
            return map_s;
        }
        // 创建集合
        map_s.addAll(Arrays.asList(student));

        // 反转集合
        Collections.reverse(map_s);

        // 删除王姓
        map_s.removeIf(StudentTest::isStartWithWang);
        // 打印剩余
        for (String nt : map_s) {
            System.out.println(nt);
        }
        return map_s;
    }


    public static void main(String[] args) {
        StudentTest st = new StudentTest();
        Scanner nc = new Scanner(System.in);
        String st_in = nc.nextLine();
        st.processInputSting(st_in);
    }
}
