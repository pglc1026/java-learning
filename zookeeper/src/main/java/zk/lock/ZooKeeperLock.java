package zk.lock;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author jabriel
 */
@Slf4j
public class ZooKeeperLock implements Watcher {

    private ZooKeeper zk = null;
    /**
     * 锁的根节点
     */
    private String rootLockNode;
    /**
     * 竞争资源，用来生成子节点名称
     */
    private String lockName;
    /**
     * 当前锁
     */
    private String currentLock;
    /**
     * 等待的锁（前一个锁）
     */
    private String waitLock;
    /**
     * 计数器（用来在加锁失败时阻塞加锁线程）
     */
    private CountDownLatch countDownLatch;
    /**
     * 超时时间
     */
    private int sessionTimeout = 30000;

    /**
     * 1.构造器中创建ZK链接
     * 2.初始化锁的根节点
     *
     * @param zkAddress
     * @param rootLockNode
     * @param lockName
     */
    public ZooKeeperLock(String zkAddress, String rootLockNode, String lockName) {
        this.rootLockNode = rootLockNode;
        this.lockName = lockName;
        try {
            // 创建连接，zkAddress格式为：IP:PORT
            zk = new ZooKeeper(zkAddress, this.sessionTimeout, this);
            // 检测锁的根节点是否存在，不存在则创建
            Stat stat = zk.exists(rootLockNode, false);
            if (null == stat) {
                zk.create(rootLockNode, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. 加锁方法，先尝试加锁，不能加锁则等待上一个锁的释放
     */
    public boolean lock() {
        if (this.tryLock()) {
            log.info("线程{},加锁{}成功！", Thread.currentThread().getName(), this.currentLock);
            return true;
        } else {
            return waitOtherLock(this.waitLock, this.sessionTimeout);
        }
    }

    /**
     * 3. 释放锁
     */
    public void unlock() throws InterruptedException {
        try {
            Stat stat = zk.exists(this.currentLock, false);
            if (null != stat) {
                log.info("线程{}释放锁 " + this.currentLock);
                zk.delete(this.currentLock, -1);
                this.currentLock = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            zk.close();
        }
    }

    public boolean tryLock() {
        // 分隔符
        String split = "_lock_";
        if (this.lockName.contains("_lock_")) {
            throw new RuntimeException("lockName can't contains '_lock_' ");
        }
        try {
            // 创建锁节点（临时有序节点）
            this.currentLock = zk.create(this.rootLockNode + "/" + this.lockName + split, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            log.info("线程{},创建锁节点{} 成功，开始竞争...", Thread.currentThread().getName(), this.currentLock);

            // 取所有子节点
            List<String> nodes = zk.getChildren(this.rootLockNode, false);

            // 取所有竞争lockName的 节点
            List<String> lockNodes = Lists.newArrayList();
            for (String nodeName : nodes) {
                if (nodeName.split(split)[0].equals(this.lockName)) {
                    lockNodes.add(nodeName);
                }
            }
            Collections.sort(lockNodes);

            // 取最小节点与当前锁节点比对加锁
            String currentLockPath = this.rootLockNode + "/" + lockNodes.get(0);
            if (this.currentLock.equals(currentLockPath)) {
                return true;
            }
            // 加锁失败，设置前一节点为等待锁节点
            String currentLockNode = this.currentLock.substring(this.currentLock.lastIndexOf("/") + 1);
            int preNodeIndex = Collections.binarySearch(lockNodes, currentLockNode) - 1;
            this.waitLock = lockNodes.get(preNodeIndex);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean waitOtherLock(String waitLock, int sessionTimeout) {
        boolean islock = false;
        try {
            // 监听等待锁节点
            String waitLockNode = this.rootLockNode + "/" + waitLock;
            Stat stat = zk.exists(waitLockNode, true);
            if (null != stat) {
                log.info("线程{}锁{}加锁失败，等待锁{}释放...",Thread.currentThread().getName(), currentLock, waitLock);
                // 设置计数器，使用计数器阻塞线程
                this.countDownLatch = new CountDownLatch(1);
                islock = this.countDownLatch.await(sessionTimeout, TimeUnit.MILLISECONDS);
                this.countDownLatch = null;
                if (islock) {
                    log.info("线程{}锁{}加锁成功,锁{}已经释放", Thread.currentThread().getName(), currentLock, waitLock);
                } else {
                    log.info("线程{},加锁失败...", Thread.currentThread().getName());
                }
            } else {
                islock = true;
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return islock;
    }

    /**
     * 4. 监听器回调
     *
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {

        if (null != this.countDownLatch && watchedEvent.getType() == Event.EventType.NodeDeleted) {
//            //TODO 应该得加上这个判断吧
//            System.out.println(watchedEvent.getPath().equals(waitLock));
            // 计数器减一，恢复线程操作
            this.countDownLatch.countDown();
        }
    }
}

