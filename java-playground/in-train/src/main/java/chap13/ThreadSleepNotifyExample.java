package chap13;

public class ThreadSleepNotifyExample {

    public static void main(String[] args) {
        final Object lock = new Object();

        // 线程1：等待并执行任务
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Waiting for notification...");
                try {
                    lock.wait(); // 等待被唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Woken up!");
            }
        });

        // 线程2：唤醒线程1
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(2000); // 休眠2秒后唤醒线程1
                synchronized (lock) {
                    System.out.println("Thread 2: Notifying Thread 1...");
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}