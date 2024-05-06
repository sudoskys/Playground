package week04;

/*
 * 演示线程的创建，启动，等待，唤醒，中断，销毁等操作
 * */

public class ThreadPlayground {
    public static void main(String[] args) {
        // 创建线程
        Thread thread = new Thread(() -> {
            System.out.println("Hello, I am a thread.");
        });
        // 启动线程
        thread.start();
        // 等待线程结束
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断线程
        thread.interrupt();
        // 销毁线程
        thread = null;
    }
}
