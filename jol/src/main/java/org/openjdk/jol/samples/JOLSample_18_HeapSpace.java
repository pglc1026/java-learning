package org.openjdk.jol.samples;

import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/12
 */
public class JOLSample_18_HeapSpace {

    public static void main(String[] args) {

        PrintWriter pw = new PrintWriter(System.out, true);

        //生成一个新的对象，记录其地址
        long last = VM.current().addressOf(new Object());
        for (int l = 0; l < 1000 * 1000 * 1000; l++) {
            //循环生成新的对象，并记录当前地址
            long current = VM.current().addressOf(new Object());

            //计算当前地址和上次（last）的区域大小
            //注意：这里时距离的绝对值，因为有GC
            long distance = Math.abs(current - last);
            //当区域间隔大于4096 bytes 时输出内存分配情况
            if (distance > 4096) {
                //打印内存从 last 跳转到 current 的距离
                pw.printf("Jumping from %x to %x (distance = %d bytes, %dK, %dM)%n",
                        last,
                        current,
                        distance,
                        distance / 1024,
                        distance / 1024 / 1024);
            }

            last = current;
        }

        pw.close();
    }

}
