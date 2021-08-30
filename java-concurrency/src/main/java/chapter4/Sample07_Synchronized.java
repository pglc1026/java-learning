package chapter4;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/13
 */
public class Sample07_Synchronized {

    public static void main(String[] args) {
        synchronized (Sample07_Synchronized.class) {
        }
        // 静态同步方法，对 Sample07_Synchronized class 对象进行加锁
        m();
    }

    public static synchronized void m() {
    }

}
