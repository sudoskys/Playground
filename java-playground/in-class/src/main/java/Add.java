/**
 * 定义一个名为 add 的静态方法，返回值为 int，参数数量可变，且为 int，将这些参数相加后返回。
 */

public class Add {
    // 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码
    /********* Begin *********/
    public static int add(int... a) {
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        return sum;
    }


    /********** End **********/
}