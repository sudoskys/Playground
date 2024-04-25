package chap13;

public class ThreadCreate {

    public static void main(String[] args) {
        // 创建并启动线程
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new MyThread();
        Thread thread3 = new Thread(() -> System.out.println("Lambda thread"));

        thread1.start();
        thread2.start();
        thread3.start();

        // 演示线程状态
        System.out.println("Thread 1 State: " + thread1.getState());
        System.out.println("Thread 2 State: " + thread2.getState());
        System.out.println("Thread 3 State: " + thread3.getState());

        // 演示线程优先级
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.NORM_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);

        // 演示线程休眠
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 演示线程让步
        Thread.yield();

        // 演示插队
        try {
            thread1.join(); // 等待 thread1 执行完毕
            thread2.join(); // 等待 thread2 执行完毕
            thread3.join(); // 等待 thread3 执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 演示多线程同步
        Counter counter = new Counter();
        Thread t1 = new Thread(new IncrementTask(counter));
        Thread t2 = new Thread(new IncrementTask(counter));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Counter Value: " + counter.getCount());
    }
}

// 线程方式1：实现 Runnable
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable thread");
    }
}

// 线程方式2：继承 Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread class thread");
    }
}

// 多线程同步示例
class Counter {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

// 多线程同步任务
class IncrementTask implements Runnable {
    private Counter counter;

    public IncrementTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}