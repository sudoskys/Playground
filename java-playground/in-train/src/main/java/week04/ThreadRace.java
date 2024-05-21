package week04;

public class ThreadRace {
    private int count = 0;
    private final Object lock = new Object();

    class Thread1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                synchronized (lock) {
                    System.out.println("Thread1 start" + count);
                    System.out.println("============");
                    System.out.println("content" + count);
                    count++;
                    System.out.println("============");
                    System.out.println("Thread1 end" + count);
                    System.out.println("\n");
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class Thread2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                synchronized (lock) {
                    System.out.println("Thread2 start" + count);
                    System.out.println("============");
                    System.out.println("content of" + count);
                    count++;
                    System.out.println("============");
                    System.out.println("Thread2 end" + count);
                    System.out.println("\n");
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadRace threadRace = new ThreadRace();
        threadRace.new Thread1().start();
        threadRace.new Thread2().start();
    }
}