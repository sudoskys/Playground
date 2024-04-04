// 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码
/********** Begin **********/
// 声明一个名为 com.com.test 的包
package Object;

// 导入 Movie 类

import Object.Movie;

// 导入 java.util.Scanner 类
import java.util.Scanner;


// 定义一个公开的 Test 类
public class Test {
    // 定义主方法
    public static void main(String[] args) {
        // 实例化 Movie 对象
        Movie movie = new Movie();
        // 将键盘四次输入的电影信息赋值给 Movie 对象
        Scanner scanner = new Scanner(System.in);
        movie.setName(scanner.nextLine());
        movie.setType(scanner.nextLine());
        movie.setTime(scanner.nextLine());
        movie.setArea(scanner.nextLine());
        // 调用获取 Movie 信息的方法
        movie.info();
    }
}
/*** End **********/