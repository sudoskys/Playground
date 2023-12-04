/*
 * 根据所学的 Integer 类的相关知识，完成本关任务。
 */
public class Test {
    public static void main(String[] args) {
        // 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码
        /********* Begin **********/
        // 创建一个 Integer 对象，它表示指定的 int 值，值为 “123”，变量名命名为 i1
        Integer i1 = new Integer(123);
        // 创建一个 Integer 对象，它表示 String 参数所指示的 int 值，值为 “123”，变量名命名为 i2
        Integer i2 = Integer.valueOf("123");
        // 依次使用 == 和 equals 方法比较 i1 和 i2 是否相同
        boolean one = i1 == i2;
        System.out.printf("i1==i2的结果为%b\n", one);
        boolean two = i1.equals(i2);
        System.out.printf("i1.equals(i2)的结果为%b\n", two);
        // 使用自动装箱的方法创建一个 Integer 对象，值为 “128”，变量名命名为 i3
        Integer i3 = 128;
        // 使用 compareTo 方法 依次比较 i3 和 i1、i2的大小以及 ii 和 i2 的大小
        System.out.printf("i3和i1比较后的结果为%d\n", i3.compareTo(i1));
        // 将 i2 转换为 int 类型，i1 转换为 String 类型
        System.out.printf("i3和i2比较后的结果为%d\n", i3.compareTo(i2));

        System.out.printf("i1和i2比较后的结果为%d\n", i1.compareTo(i2));

        int i4 = i2.intValue();
        System.out.printf("Integer->int结果为%d\n", i4);
        System.out.printf("Integer->String结果为%s", i1.toString());

        /********** End **********/

    }
}
