package chapter4.util;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/6/13
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.err.println("Thread : " + Thread.currentThread().getName());
            System.err.println(e);
        }
    }

}
