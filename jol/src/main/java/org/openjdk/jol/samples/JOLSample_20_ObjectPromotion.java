package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/12
 */
public class JOLSample_20_ObjectPromotion {

    // 垃圾对象
    static volatile Object sink;

    public static void main(String[] args) {

        PrintWriter pw = new PrintWriter(System.out, true);

        //创建目标对象
        Object o = new Object();

        ClassLayout layout = ClassLayout.parseInstance(o);

        long lastAddr = VM.current().addressOf(o);
        pw.printf("*** Fresh object is at %x%n", lastAddr);
        System.out.println(layout.toPrintable());

        //对象移动的次数
        int moves = 0;
        for (int i = 0; i < 100000; i++) {
            //对象当前内存地址
            long cur = VM.current().addressOf(o);
            if (cur != lastAddr) {
                moves++;
                pw.printf("*** Move %2d, object is at %x%n", moves, cur);
                System.out.println(layout.toPrintable());
                lastAddr = cur;
            }

            // 制造垃圾对象
            for (int c = 0; c < 10000; c++) {
                sink = new Object();
            }
        }

        long finalAddr = VM.current().addressOf(o);
        pw.printf("*** Final object is at %x%n", finalAddr);
        System.out.println(layout.toPrintable());

        pw.close();
    }

}
