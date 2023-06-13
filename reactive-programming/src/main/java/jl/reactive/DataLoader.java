package jl.reactive;

import java.util.concurrent.TimeUnit;

/**
 * DataLoader
 *
 * @author Liu Chang
 * @date 2019/11/1
 */
public class DataLoader {

    public final void load() {
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 具体执行
        doLoad();
        // 消耗时间
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("load() 总耗时：" + costTime + " ms");
    }

    /**
     * 串行计算
     * 总耗时：1s + 2s + 3s = 6s
     */
    protected void doLoad() {
        // 耗时1s
        loadConfigurations();
        // 耗时2s
        loadUsers();
        // 耗时3s
        loadOrders();
    }

    protected final void loadConfigurations() {
        loadMock("loadConfigurations()", 1);
    }

    protected final void loadUsers() {
        loadMock("loadUsers()", 2);
    }

    protected final void loadOrders() {
        loadMock("loadOrders()", 3);
    }

    private void loadMock(String source, int seconds) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(milliseconds);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程 : %s] %s 耗时 : %d 毫秒\n", Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new DataLoader().load();
    }

}
