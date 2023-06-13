package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;


/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_01_Basic {

    public static void main(String[] args) {
        //输出当前虚拟机的信息
        System.out.println(VM.current().details());
        //输出类A 的内存布局
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
        boolean f;
    }

}
