package jl.clhlock;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Liu Chang
 * @date 2020/9/3
 */
public class CLHLock {

    /**
     * CLH锁节点
     */
    private static class CLHNode {
        // 锁状态：默认为false，表示线程没有获取到锁；true表示线程获取到锁或正在等待
        // 为了保证locked状态是线程间可见的，因此用volatile关键字修饰
        volatile boolean locked = false;
    }

    /**
     * CLH锁尾节点
     */
    private final AtomicReference<CLHNode> tailNode;

    /**
     * 当前节点
     */
    private final ThreadLocal<CLHNode> curNode;

    /**
     * 当前节点的前继节点
     */
    private final ThreadLocal<CLHNode> predNode;

    public CLHLock() {
        // 初始化尾节点，指向一个新的CLHNode
        tailNode = new AtomicReference<>(new CLHNode());
        // 初始化当前节点
        curNode = ThreadLocal.withInitial(CLHNode::new);
        // 初始化当前节点的前继节点
        predNode = new ThreadLocal<>();
    }

    public void lock() {
        // 获取当前节点
        CLHNode cur = curNode.get();
        // 将当前节点locked设置为true，表示正在等锁或者已经拿到锁
        cur.locked = true;
        // 将当前节点放入尾节点，并获取之前的尾节点（前一节点的当前节点）
        // 【注意】在多线程并发情况下，这里通过AtomicReference类能防止并发问题
        // 【注意】哪个线程先执行到这里就会先执行predNode.set(preNode);语句，因此构建了一条逻辑线程等待链
        // 这条链避免了线程饥饿现象发生
        CLHNode preNode = tailNode.getAndSet(cur);
        // 将前置节点放入当前节点的前置节点
        predNode.set(preNode);
        // 对前置节点的locked状态进行自旋
        while (preNode.locked) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                // do nothing
            }
            System.out.println("线程" + Thread.currentThread().getName() + "未获取到锁，将进行自旋。");
        }

        // 走到这里表示已经拿到锁了
        System.out.println("线程" + Thread.currentThread().getName() + "成功获取到锁。");
    }

    public void unlock() {
        // 获取当前节点
        CLHNode cur = curNode.get();
        // 将当前节点置为false
        cur.locked = false;

        System.out.println("线程" + Thread.currentThread().getName() + "释放了锁。");

        // 创建一个新节点，并放入尾节点中
        CLHNode clhNode = new CLHNode();
        tailNode.set(clhNode);
    }
}
