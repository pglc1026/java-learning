package jl.jdk8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureTest5
 * 只对CompletableFuture的结果进行消费,无返回值,也就是最后的CompletableFuture是void.
 *
 * accept
 * public CompletableFuture<Void>  thenAccept(Consumer<? super T> action)
 * public CompletableFuture<Void>  thenAcceptAsync(Consumer<? super T> action)
 * public CompletableFuture<Void>  thenAcceptAsync(Consumer<? super T> action, Executor executor)
 *
 * thenAcceptBoth
 * 这个方法用来组合两个CompletableFuture,其中一个CompletableFuture等待另一个CompletableFuture的结果.
 *
 * @author Liu Chang
 * @date 2019/9/20
 */
public class CompletableFutureTest5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world");

//        future.thenAccept(e -> System.out.println("without return value"));
//        future.get();

        CompletableFuture future5 = future.thenAcceptBoth(CompletableFuture.completedFuture(" compose"), (x, y) -> System.out.println(x + y));

        future5.get();
    }
}
