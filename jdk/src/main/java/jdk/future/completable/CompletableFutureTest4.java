package jdk.future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutureTest4
 *
 * CompletableFuture的组合
 * thenApply
 * 当计算结算完成之后,后面可以接继续一系列的thenApply,来完成值的转化.
 * public <U> CompletableFuture<U>     thenApply(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U>     thenApplyAsync(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U>     thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 *
 * 它们与handle方法的区别在于handle方法会处理正常计算值和异常，因此它可以屏蔽异常，避免异常继续抛出。而thenApply方法只是用来处理正常值，因此一旦有异常就会抛出。
 *
 * @author Liu Chang
 * @date 2019/9/20
 */
public class CompletableFutureTest4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world");
        CompletableFuture<String> future2 = future.thenApply(element -> element + " addOnePart")
                .thenApply(ele -> {
                    throw new RuntimeException();
                })
                .thenApply(element -> element + " addTwoPart");
        System.out.println(future2.get());
    }
}
