package org.openjdk.jol.samples;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/11
 */
public class JOLSample_07_Throwable {

    public static void main(String[] args) {
        out.println(ClassLayout.parseClass(Throwable.class).toPrintable());
    }

}
