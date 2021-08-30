package org.openjdk.jol.samples;

import org.openjdk.jol.info.GraphLayout;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/12
 */
public class JOLSample_17_ReferenceFootprint {

    public static void main(String[] args) {

        ArrayList<Integer> al = new ArrayList<>();
        LinkedList<Integer> ll = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            Integer io = i; // box once
            al.add(io);
            ll.add(io);
        }

        al.trimToSize();// 将ArrayList的大小调整到已存储对象的位置

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(GraphLayout.parseInstance(al).toFootprint());
        pw.println(GraphLayout.parseInstance(ll).toFootprint());
        pw.println(GraphLayout.parseInstance(al, ll).toFootprint());
        pw.close();
    }

}
