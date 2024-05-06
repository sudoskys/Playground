package week03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SumTest {
    //定义线程池
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        List<Future<Long>> list = new ArrayList<>();
        long totalSum = 0;
        try {
            // 创建10个线程，并且提交任务
            for (int i = 0; i < 10; i++) {
                int start = i * 10000;
                int end = (i + 1) * 10000 - 1;
                Future<Long> future = executor.submit(new SumTask(start, end));
                list.add(future);
            }
            // 得到并行计算的结果并累加
            for (Future<Long> future : list) {
                totalSum += future.get();
            }
            System.out.println("总和 = " + totalSum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 释放线程池资源
            executor.shutdown();
        }
    }
}

class SumTask implements Callable<Long> {
    private final int start;
    private final int end;

    //构造函数
    SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}