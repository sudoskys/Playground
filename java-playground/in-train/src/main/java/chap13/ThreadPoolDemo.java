package chap13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 创建一个固定大小为3的线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);

        AtomicInteger counter = new AtomicInteger(1); // 使用原子整数保证线程安全的计数

        // 提交5个任务给线程池
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                int currentThreadNum = counter.getAndIncrement(); // 获取并自增计数

                System.out.println("Task " + currentThreadNum + " is running by Thread " + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}