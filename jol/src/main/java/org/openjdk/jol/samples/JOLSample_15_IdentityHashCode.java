package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/12
 */
public class JOLSample_15_IdentityHashCode {

    public static void main(String[] args) {

        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);

        System.out.println("**** Fresh object");
        System.out.println(layout.toPrintable());

        System.out.println("hashCode: " + Integer.toHexString(a.hashCode()));
        System.out.println();

        System.out.println("**** After identityHashCode()");
        System.out.println(layout.toPrintable());
    }

    public static class A {
        // no fields
    }

}
