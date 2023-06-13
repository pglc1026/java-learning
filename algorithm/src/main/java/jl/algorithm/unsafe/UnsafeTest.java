package jl.algorithm.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * UnsafeTest
 *
 * @author pglc1026
 * @date 2019-07-06
 */
public class UnsafeTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");

        singleoneInstanceField.setAccessible(true);

        Unsafe unsafe = (Unsafe) singleoneInstanceField.get(null);

        MyObject myObject1 = new MyObject("1");
        MyObject myObject2 = new MyObject("2");

        long offset = unsafe.objectFieldOffset(MyObject.class.getDeclaredField("name"));
        boolean flag = unsafe.compareAndSwapObject(myObject1, offset, "1", "2");

        System.out.println(flag);

        System.out.println(myObject1);
        System.out.println(myObject2);
    }

    static class MyObject {
        private String name;

        public MyObject(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyObject{" + "name='" + name + '\'' + '}';
        }
    }

}
