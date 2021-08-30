package chapter10;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/14
 */
public class Sample_SynchronousQueue {

    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);

        AtomicInteger integer = new AtomicInteger(0);
        // 创建三个线程放元素
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    queue.put(integer.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "ProducerThread-" + (i + 1)).start();
        }

        // 创建三个线程消费
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    Integer take = queue.take();
                    System.out.println("*** " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "ConsumerThread-" + (i + 1)).start();
        }
    }

}
