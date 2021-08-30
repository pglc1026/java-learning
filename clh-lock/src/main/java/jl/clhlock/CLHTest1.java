package jl.clhlock;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/8/9
 */
public class CLHTest1 {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                count++;
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);

        System.out.println(count);
    }

}
