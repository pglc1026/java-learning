package jl.jdk8.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureTest7
 * Either和ALL
 * thenAcceptBoth是当两个CompletableFuture都计算完成，而我们下面要了解的方法applyToEither是当任意一个CompletableFuture计算完成的时候就会执行。
 *
 * @author Liu Chang
 * @date 2019/9/20
 */
public class CompletableFutureTest6 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } return 100;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });

        // 两个中任意一个计算完成，那么触发Runnable的执行
        CompletableFuture<String> f = future2.applyToEither(future1, i -> i.toString());
        // 两个都计算完成，那么触发Runnable的执行
//        CompletableFuture<Void> f1 = future2.acceptEither(future1, System.out::println);

        System.out.println(f.get());
    }

}
