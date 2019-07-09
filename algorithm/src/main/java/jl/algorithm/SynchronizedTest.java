package jl.algorithm;

/**
 * SynchronizedTest
 *
 * @author pglc1026
 * @date 2019-07-07
 */
public class SynchronizedTest {

    private static int i = 0;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        synchronized (SynchronizedTest.class) {
            System.out.println(i++);
            if (i == 1000) {
                return;
            }
            test();
        }
    }

}
