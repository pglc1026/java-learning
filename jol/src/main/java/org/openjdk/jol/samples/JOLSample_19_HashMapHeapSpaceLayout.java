package org.openjdk.jol.samples;

import org.openjdk.jol.info.GraphLayout;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/12
 */
public class JOLSample_19_HashMapHeapSpaceLayout {

    public static void main(String[] args) {

        PrintWriter pw = new PrintWriter(System.out, true);

        Map<Dummy, Void> map = new HashMap<>();

        //添加两个不同 hash 值的对象
        map.put(new Dummy(1), null);
        map.put(new Dummy(2), null);

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        //生成 hash 相同的对象，并放入 HashMap
        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        //继续生成更多相同 hash 值的对象，目的是看到链表如何转换为树
        for (int c = 0; c < 12; c++) {
            map.put(new Dummy(2), null);
        }

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        pw.close();
    }

    /**
     * Dummy class which controls the hashcode and is decently Comparable.
     */
    public static class Dummy implements Comparable<Dummy> {
        static int ID;
        final int id = ID++;
        final int hc; //hash code

        public Dummy(int hc) {
            this.hc = hc;
        }

        @Override
        public boolean equals(Object o) {
            return (this == o);
        }

        @Override
        public int hashCode() {
            return hc;
        }

        @Override
        public int compareTo(Dummy o) {
            return Integer.compare(id, o.id);
        }
    }


}
