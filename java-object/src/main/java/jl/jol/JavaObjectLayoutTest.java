package jl.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/9
 */
public class JavaObjectLayoutTest {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
        System.out.println("匿名偏向锁：");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println("偏向锁：");
            // 第一次加锁
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        Thread.sleep(5000);
        Runnable r = () -> {
            synchronized (o) {
                System.out.println("锁：");
                // 第二次加锁
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();
        Thread.sleep(5000);
        System.out.println("无锁：");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
