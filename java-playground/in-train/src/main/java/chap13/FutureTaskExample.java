package chap13;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 42;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(task);

        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            // 获取异步计算的结果，如果尚未完成会阻塞当前线程
            Integer result = futureTask.get();
            System.out.println("Result: " + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}