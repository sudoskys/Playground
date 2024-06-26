/**
 * 任务：编写一个商品结算的小程序
 * 类名为：Shop
 */

// 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码

/********** Begin **********/

public class Shop {
    // 定义该商品的两个属性：价格（double）和数量（int）
    private double price;
    private int total;


    // 将形参的值赋值给成员变量
    public Shop(double price, int total) {
        this.price = price;
        this.total = total;
    }

    // 该方法实现计算价钱的功能，将计算结果返回，价钱 = 价格 * 数量
    public double sum() {
        return price * total;
    }
}

/********** End **********/
