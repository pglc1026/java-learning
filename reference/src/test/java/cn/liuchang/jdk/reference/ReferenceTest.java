package cn.liuchang.jdk.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * ReferenceTest
 *
 * @author pglc1026
 * @date 2019-04-08
 */
public class ReferenceTest {

    @Test
    public void TestSoftReference() throws Exception {
        MyObject obj = new MyObject();
        SoftReference sr = new SoftReference<>(obj);
        obj = null;
        System.gc();
        byte[] bytes = new byte[1024 * 100];
        System.gc();

        System.out.println("是否被回收" + sr.get());
    }

    @Test
    public void TestWeakReference() throws Exception {
        MyObject obj = new MyObject();
        WeakReference wr = new WeakReference<>(obj);
        obj = null;
        System.out.println("是否被回收" + wr.get());
        System.gc();
        System.out.println("是否被回收" + wr.get());
    }

    @Test
    public void TestPhantomReference() {
        MyObject obj = new MyObject();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference pr = new PhantomReference<>(obj, referenceQueue);
        obj = null;
        System.out.println("是否被回收" + pr.get());
        System.gc();
        System.out.println("是否被回收" + pr.get());
    }

    @Test
    public void TestWeakHashMap() {
        Map<String, Object> map;
        map = new WeakHashMap<>();
        for (int i = 0; i < 7000; i++) {
            map.put("key" + i, new byte[i]);
        }


    }

    @Test
    public void TestHashMap() {
        Map<String, Object> map;
        map = new HashMap<>();
        for (int i = 0; i < 7000; i++) {
            map.put("key" + i, new byte[i]);
        }
    }

}
