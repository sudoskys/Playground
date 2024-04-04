public class Single {
    private static Single instance = null;

    private Single() {
    }

    public static Single getInstance() {
        if (instance == null) {
            instance = new Single();
        }
        return instance;
    }

    public static void think() {
        System.out.println("I think, therefore I am.");
    }

    public static void main(String[] args) {
        // 测试
        Single s1 = Single.getInstance();
        Single s2 = Single.getInstance();
        System.out.println(s1 == s2);
        // 输出内存地址
        System.out.println(s1);
        System.out.println(s2);
    }
}

// Path: src/main/java/Single.java