package week04;
/*
 * 采用阻塞队列LinkedBlockQueue<E>完成生产者和消费者的同步问题，要求主线程先执行，在主线程中使用Thread类创建子线程（生产者和消费者），主线程创建后进入阻塞状态，直到子线程运行完毕后唤醒主线程。
 * 正确使用synchronized关键字、特殊域变量（volatile）、ReentrantLock类和阻塞队列LinkedBlockQueue<E>这几种同步方法，进一步理解线程的同步。
4、实验指导
（1）具体的操作过程同实验一
（2）使用LinkedBlockingQueue<E>来实现线程的同步，在实验中定义一个阻塞队列LinkedBlockingQueue<Integer>queue 用来存储生产出来的商品，定义启动线程的标志flag，当该值为0时，启动生产商品的线程，当该值为1时，启动消费商品的线程。
*
* 启动线程 0
* 生产商品 165 号
* 仓库中商品数量：1
* 消费商品 165 号
* 仓库中商品数量：0
* 生产商品 166 号
* 仓库中商品数量：1
* 生产商品 167 号
* 仓库中商品数量：2
* 生产商品 168 号
* 仓库中商品数量：3
 * */

import java.util.concurrent.LinkedBlockingQueue;

class SharedFlag {
    public volatile int flag = 0;
}

class Producer extends Thread {
    private LinkedBlockingQueue<Integer> queue;
    private int num;
    private SharedFlag sharedFlag;

    public Producer(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.put(num);
                System.out.println("生产商品 " + num + " 号");
                System.out.println("仓库中商品数量：" + queue.size());
                num++;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private LinkedBlockingQueue<Integer> queue;
    private SharedFlag sharedFlag;

    public Consumer(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int num = queue.take();
                System.out.println("消费商品 " + num + " 号");
                System.out.println("仓库中商品数量：" + queue.size());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadObstruct {
    public static <LinkedBlockQueue> void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        // 定义启动线程的标志flag，当该值为0时，启动生产商品的线程，当该值为1时，启动消费商品的线程。
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        producer.start();
        consumer.start();
        // 主线程等待子线程结束
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}