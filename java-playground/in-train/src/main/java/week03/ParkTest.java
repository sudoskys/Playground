package week03;

import java.util.Random;
import java.util.concurrent.*;

public class ParkTest {

    private static final int PARKING_SPOTS = 10;

    // Semaphore对象代表停车场
    private final Semaphore semaphore = new Semaphore(PARKING_SPOTS, true);

    class Vehicle implements Runnable {
        @Override
        public void run() {
            try {
                // 获取许可证
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " has acquired a parking spot.");
                // 停车时间模拟
                Thread.sleep(new Random().nextInt(10) * 1000);
                System.out.println(Thread.currentThread().getName() + " has left the parking spot.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 返回许可证
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        ParkTest parkTest = new ParkTest();
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        // 从第 0s开始每隔2s产生一个Vehicle线程
        pool.scheduleAtFixedRate(parkTest.new Vehicle(), 0, 2, TimeUnit.SECONDS);
    }
}