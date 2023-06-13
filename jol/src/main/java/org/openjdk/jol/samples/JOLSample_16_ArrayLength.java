package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/12
 */
public class JOLSample_16_ArrayLength {

    public static void main(String[] args) {

        //分别创建了8个int 类型的数组，并输出每个数组的内存布局
        for (int c = 0; c < 8; c++) {
            System.out.println("**** int[" + c + "]");
            System.out.println(ClassLayout.parseInstance(new int[c]).toPrintable());
        }
    }

}
