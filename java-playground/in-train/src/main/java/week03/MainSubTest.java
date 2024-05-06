package week03;

public class MainSubTest {
    private boolean subThreadExecute = true;

    public static void main(String[] args) {
        MainSubTest test = new MainSubTest();
        Runnable sub = () -> test.runSub();
        Runnable main = () -> test.runMain();

        new Thread(sub).start();
        new Thread(main).start();
    }

    public synchronized void runSub() {
        for (int i = 0; i < 10; i++) {
            while (!subThreadExecute) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 10; j++) {
                System.out.println("SubThread running round: " + j);
            }
            subThreadExecute = false;
            notify();
        }
    }

    public synchronized void runMain() {
        for (int i = 0; i < 10; i++) {
            while (subThreadExecute) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 3; j++) {
                System.out.println("MainThread running round: " + j);
            }
            subThreadExecute = true;
            notify();
        }
    }
}