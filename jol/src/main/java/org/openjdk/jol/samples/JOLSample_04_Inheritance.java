package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_04_Inheritance {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(C.class).toPrintable());
    }

    public static class A {
        int a;
    }

    public static class B extends A {
        int b;
    }

    public static class C extends B {
        int c;
    }

}
