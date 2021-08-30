package jl.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_HC_BL_Conflict {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);

        Object o = new Object();
        System.out.println("**** Fresh object");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println("**** With the lock");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        System.out.println("**** After the lock");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println("hashCode: " + o.hashCode());

        System.out.println("**** After the hashCode");

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println("**** With the second lock");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        System.out.println("**** After the second lock");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

    }

}
