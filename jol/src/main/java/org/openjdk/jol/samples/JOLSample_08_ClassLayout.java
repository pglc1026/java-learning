package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_08_ClassLayout {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(Class.class).toPrintable());
    }

}
