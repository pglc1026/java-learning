package chapter10;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/14
 */
public class Sample_CyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("**** 阶段结束，开始下一阶段 ****"));

        for (int i = 0; i < 5; i++) {
            new Thread(new Interviewer(barrier), "Interviewer-" + (i + 1)).start();
        }
    }


    static class Interviewer implements Runnable {

        private final Random random = new Random();
        private final CyclicBarrier barrier;

        public Interviewer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " - 准备出发去公司");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
                System.out.println(Thread.currentThread().getName() + " - 到达公司了");
                barrier.await();

                System.out.println(Thread.currentThread().getName() + " - 开始笔试");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
                System.out.println(Thread.currentThread().getName() + " - 笔试结束");
                barrier.await();

                System.out.println(Thread.currentThread().getName() + " - 开始面试");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
                System.out.println(Thread.currentThread().getName() + " - 面试结束");
            } catch (Exception e) {
            }
        }
    }
}
