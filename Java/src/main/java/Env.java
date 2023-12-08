public class Env {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.getenv().forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        // 设置环境变量
        System.setProperty("name", "张三");
        System.out.println(System.getProperty("name"));
    }
}
