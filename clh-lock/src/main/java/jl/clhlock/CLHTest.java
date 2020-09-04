package jl.clhlock;

/**
 * @author Liu Chang
 * @date 2020/9/4
 */
public class CLHTest {

    // 定义一个静态成员变量cnt，然后开10个线程跑起来，看能是否会有线程安全问题
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        CLHLock lock = new CLHLock();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                lock.lock();

                count++;

                lock.unlock();
            }).start();
        }

        Thread.sleep(20000L);

        System.out.println("count ----------> " + count);
    }
}
