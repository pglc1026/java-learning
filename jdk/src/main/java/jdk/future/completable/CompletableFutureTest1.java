package jdk.future.completable;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureTest
 * 创建、同步获取结果
 *
 * 创建CompletableFuture的方式
 * public static CompletableFuture<Void> runAsync(Runnable runnable)
 * public static CompletableFuture<Void>   runAsync(Runnable runnable, Executor executor)
 * public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier)
 * public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier, Executor executor)
 *
 * 同步获取结果
 * public T    get()
 * public T    get(long timeout, TimeUnit unit)
 * public T    getNow(T valueIfAbsent)
 * public T    join()

 * @author Liu Chang
 * @date 2019/9/19
 */
public class CompletableFutureTest1 {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadFactoryBuilder().setNameFormat("jl-completable-future-%d").build());

    public static void main(String[] args) throws Exception {

        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world " + Thread.currentThread().getName(), executor);

//            Thread.sleep(1000);

            System.out.println(future.getNow("hello java"));

            // 阻塞的获取结果
            System.out.println(future.get());

            System.out.println(future.getNow("hello java"));

        } finally {
            executor.shutdown();
        }
    }

}
