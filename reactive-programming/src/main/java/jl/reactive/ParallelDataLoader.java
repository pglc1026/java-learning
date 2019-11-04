package jl.reactive;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ParallelDataLoader
 *
 * @author Liu Chang
 * @date 2019/11/1
 */
public class ParallelDataLoader extends DataLoader {

    /**
     * 并行计算
     */
    protected void doLoad() {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorCompletionService completionService = new ExecutorCompletionService(executorService);
        // 耗时 >= 1s
        completionService.submit(super::loadConfigurations, null);
        // 耗时 >= 2s
        completionService.submit(super::loadUsers, null);
        // 耗时 >= 3s
        completionService.submit(super::loadOrders, null);

        int count = 0;
        // 等待三个任务完成
        while (count < 3) {
            if (completionService.poll() != null) {
                count++;
            }
            executorService.shutdown();
        }   // 总耗时 max(1s, 2s, 3s) >= 3s

    }

    public static void main(String[] args) {
        new ParallelDataLoader().load();
    }

}
