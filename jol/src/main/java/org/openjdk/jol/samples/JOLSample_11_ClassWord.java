package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_11_ClassWord {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new A()).toPrintable());
        System.out.println(ClassLayout.parseInstance(new B()).toPrintable());
    }

    public static class A {
        // no fields
    }

    public static class B {
        // no fields
    }

}
