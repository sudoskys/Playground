package chap13;

import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        // 获取Class对象
        Class<?> cls = Class.forName("chap13.T2");

        // 创建对象
        Object obj = cls.getDeclaredConstructor().newInstance();

        // 获取方法
        Method[] methods = cls.getDeclaredMethods();

        // 调用方法
        for (Method method : methods) {
            System.out.println("Method name: " + method.getName());
            method.invoke(obj);
        }
    }
}