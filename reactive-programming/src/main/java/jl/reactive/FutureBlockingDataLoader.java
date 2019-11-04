package jl.reactive;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * FutureBlockingDataLoader
 *
 * @author Liu Chang
 * @date 2019/11/1
 */
public class FutureBlockingDataLoader extends DataLoader {

    protected void doLoad() {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 耗时 >= 1s
        runCompletely(executorService.submit(super::loadConfigurations));
        // 耗时 >= 2s
        runCompletely(executorService.submit(super::loadUsers));
        // 耗时 >= 3s
        runCompletely(executorService.submit(super::loadOrders));

        executorService.shutdown();
    } // 总耗时 sum(>= 1s, >=2s, >=3s) >= 6s

    private void runCompletely(Future<?> future) {
        // 阻塞等待结果
        try {
            future.get();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new FutureBlockingDataLoader().load();
    }
}
