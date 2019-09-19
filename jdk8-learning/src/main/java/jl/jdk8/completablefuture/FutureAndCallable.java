package jl.jdk8.completablefuture;
import java.time.LocalDateTime;
import java.util.concurrent.*;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * FutureAndCallable
 *
 * @author Liu Chang
 * @date 2019/9/19
 */
public class FutureAndCallable {

    private static ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("jl-pool-%d").build();

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 10000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1), factory);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 定义一个异步任务
        System.out.println(LocalDateTime.now().toString());
        Future<String> future = executor.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            return "hello world";
        });

        // 轮询结果
        while (true) {
            if (future.isDone()) {
                System.out.println(future.get());
                System.out.println(LocalDateTime.now());
                break;
            }
        }

        executor.shutdown();

    }

}
