package jdk.future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureTest3
 * 结算结果完成时的处理
 *
 * public CompletableFuture<T>     whenComplete(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 * public CompletableFuture<T>     exceptionally(Function<Throwable,? extends T> fn)
 *
 * handle()
 * public <U> CompletableFuture<U>     handle(BiFunction<? super T,Throwable,? extends U> fn)
 * public <U> CompletableFuture<U>     handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
 * public <U> CompletableFuture<U>     handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)
 *
 * @author Liu Chang
 * @date 2019/9/19
 */
public class CompletableFutureTest3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world");
        future.whenCompleteAsync((v, e) -> {
            System.out.println("return value: " + v + " exception: " + e);
        });

        CompletableFuture<String> future2 = future.handle((v, e) -> v.replace("world", "java"));

        System.out.println(future2.get());
    }
}
