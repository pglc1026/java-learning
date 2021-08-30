package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/12
 */
public class JOLSample_12_BiasedLocking {

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5000);

        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);

        System.out.println("**** 新对象");
        System.out.println(layout.toPrintable());

        synchronized (a) {
            System.out.println("**** 持有对象锁");
            System.out.println(layout.toPrintable());
        }

        System.out.println("**** 释放锁");
        System.out.println(layout.toPrintable());
    }

    public static class A {
        // no fields
    }

}
