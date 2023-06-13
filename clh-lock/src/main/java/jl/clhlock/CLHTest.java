package jl.clhlock;

import java.util.concurrent.TimeUnit;

/**
 * @author Liu Chang
 * @date 2020/9/4
 */
public class CLHTest {

    // 定义一个静态成员变量cnt，然后开10个线程跑起来，看能是否会有线程安全问题
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        CLHLock lock = new CLHLock();
//        for (int i = 0; i < 100000; i++) {
//            new Thread(() -> {
//                lock.lock();
//
//                count++;
//
//                lock.unlock();
//            }).start();
//        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            lock.lock();

            System.out.println(Thread.currentThread().getName() + "获取到锁");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }

            count++;
            lock.unlock();
        }, "1").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            lock.lock();

            System.out.println(Thread.currentThread().getName() + "获取到锁");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
            }
            count++;
            lock.unlock();
        }, "2").start();

        TimeUnit.SECONDS.sleep(5);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            lock.lock();

            System.out.println(Thread.currentThread().getName() + "获取到锁");
            count++;
            lock.unlock();
        }, "3").start();

//        Thread.sleep(20000L);

        System.out.println("count ----------> " + count);
    }
}
