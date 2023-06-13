package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_06_HierarchyGaps {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(C.class).toPrintable());
    }

    public static class A {
        boolean a;
    }

    public static class B extends A {
        boolean b;
    }

    public static class C extends B {
        boolean c;
    }
}
