package zk.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * LockTest
 *
 * @author pglc1026
 * @date 2019-05-09
 */
public class LockTest {

    private static final String CONNECTION_INFO = "192.168.119.134:2181";

    public static void main(String[] args) {
        ZooKeeperLock lock1 = new ZooKeeperLock(CONNECTION_INFO, "/lock", "lock_test");
        ZooKeeperLock lock2 = new ZooKeeperLock(CONNECTION_INFO, "/lock", "lock_test");

        new Thread(() -> {
            try {
                boolean success = lock1.lock();
                System.err.println(Thread.currentThread().getName() + "==============" + success);
                Thread.sleep(10000L);
                lock1.unlock();
            } catch (InterruptedException e) {
                //
            }

        },"Lock-1").start();

        new Thread(() -> {

            try {
                boolean success = lock2.lock();
                System.err.println(Thread.currentThread().getName() + "==============" + success);
                Thread.sleep(10000L);
                lock2.unlock();Thread.sleep(10000L);
            } catch (InterruptedException e) {
                //
            }
        },"Lock-2").start();

        while (true) {

        }

    }

    private static CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.119.134:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("lock")
                .build();
        client.start();
        return client;
    }


}
