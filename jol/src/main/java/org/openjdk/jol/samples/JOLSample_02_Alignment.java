package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;


/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_02_Alignment {

    public static void main(String[] args) {
        //输出类A 的内存布局
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
        long f;
    }

}
