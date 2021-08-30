package jdk.future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureTest2
 * 主动触发计算
 *
 * public boolean complete(T  value)
 * public boolean completeExceptionally(Throwable ex)
 *
 * @author Liu Chang
 * @date 2019/9/19
 */
public class CompletableFutureTest2 {

    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1000;
        });
        return future;
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f = compute();

        class Client extends Thread {
            CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }

            @Override
            public void run() {
                try {
                    System.out.println(this.getName() + ":" + f.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");
        // 设置Future.get()获取到的值
//        Thread.sleep(2000);
        f.complete(100);
        // 以异常的方式触发计算
//        f.completeExceptionally(new Exception());
        Thread.sleep(1000);

    }

}
