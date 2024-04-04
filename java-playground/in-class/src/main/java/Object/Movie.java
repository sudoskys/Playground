// 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码
/********** Begin **********/

// 声明一个名为 com.model 的包
package Object;

import java.util.Scanner;

// 定义一个 Movie 的公开类

// 该类具有电影名称、电影类别、电影时长、地区等属性（都是字符串类型、私有）
public class Movie {
    private String name;
    private String type;
    private String time;
    private String area;

    // 该类具有获取和设置电影属性的方法（公开）
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    // 定义获取和设置电影属性的方法
    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    // 定义获取电影信息的方法，无返回值
    public void info() {
        System.out.println("电影名称：" + name + "，电影类别：" + type + "，电影时长：" + time + "，地区：" + area);
    }
}




