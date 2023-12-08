/**
 * 任务：实现图书类，该类包含了图书的基本属性和信息。
 * 类名为：Book
 */

// 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码

/********** Begin **********/
public class Book {
    // 定义四个私有变量
    // 图书名称（bookName String）
    // 图书单价（price double）
    // 图书库存（total int）
    // 图书id（bookId int）
    private String bookName;
    private double price;
    private int total;
    private int bookId;


    // 获取图书名称
    public String getBookName() {
        return bookName;
    }

    // 设置图书名称
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    // 获取图书单价
    public double getPrice() {
        return price;
    }

    // 设置图书单价
    public void setPrice(double price) {
        this.price = price;
    }

    // 获取图书库存
    public int getTotal() {
        return total;
    }

    // 设置图书库存
    public void setTotal(int total) {
        this.total = total;
    }

    // 获取图书id
    public int getBookId() {
        return bookId;
    }

    // 设置图书id
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}
/********** End **********/
