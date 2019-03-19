package jl.producerconsumer.optimizedlockcondition;

import jl.producerconsumer.common.AbstractConsumer;
import jl.producerconsumer.common.AbstractProducer;
import jl.producerconsumer.common.Model;
import jl.producerconsumer.common.Task;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * OptimizedLockConditionMode
 *
 * @author liuchang39
 * @date 2019/3/19
 */
public class OptimizedLockConditionMode implements Model {

    private final Lock CONSUME_LOCK = new ReentrantLock();
    private final Condition NOT_EMPTY = CONSUME_LOCK.newCondition();

    private final Lock PRODUCE_LOCK = new ReentrantLock();
    private final Condition NOT_FULL = PRODUCE_LOCK.newCondition();

    private final Buffer<Task> buffer = new Buffer<>();
    private AtomicInteger bufLen = new AtomicInteger(0);

    private final int cap;

    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public OptimizedLockConditionMode(int cap) {
        this.cap = cap;
    }


    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer {

        @Override
        public void consume() throws InterruptedException {
            int newBufSize = -1;

            CONSUME_LOCK.lockInterruptibly();
            try {
                while (bufLen.get() == 0) {
                    NOT_EMPTY.await();
                }
                Task task = buffer.poll();
                newBufSize = bufLen.decrementAndGet();
                if (newBufSize > 0) {
                    NOT_EMPTY.signalAll();
                }
                assert task != null;
                // 固定时间范围，模拟相对稳定固定服务器处理过程
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume: " + task.getNo());
            } finally {
                CONSUME_LOCK.unlock();
            }

            if (newBufSize < cap) {
                PRODUCE_LOCK.lockInterruptibly();
                try {
                    NOT_FULL.signalAll();
                } finally {
                    PRODUCE_LOCK.unlock();
                }
            }
        }
    }

    private class ProducerImpl extends AbstractProducer {

        @Override
        public void produce() throws InterruptedException {
            // 不定期生产，模拟随机的用户请求
            Thread.sleep((long) (Math.random() * 1000));

            int newBufSize = -1;

            PRODUCE_LOCK.lockInterruptibly();
            try {
                while (bufLen.get() == cap) {
                    System.out.println("buffer is full...");
                    NOT_FULL.await();
                }

                Task task = new Task(increTaskNo.getAndIncrement());
                buffer.offer(task);

                newBufSize = bufLen.getAndIncrement();
                System.out.println("produce: " + task.getNo());

                if (newBufSize < cap) {
                    NOT_FULL.signalAll();
                }
            } finally {
                PRODUCE_LOCK.unlock();
            }

            if (newBufSize > 0) {
                CONSUME_LOCK.lockInterruptibly();
                try {
                    NOT_EMPTY.signalAll();
                } finally {
                    CONSUME_LOCK.unlock();
                }
            }
        }
    }

    private static class Buffer<E> {
        private Node head;

        private Node tail;

        Buffer() {
            head = tail = new Node(null);
        }

        public void offer(E e) {
            tail.next = new Node(e);
            tail = tail.next;
        }

        public E poll() {
            head = head.next;
            E e = head.item;
            head.item = null;
            return e;
        }

        private class Node {
            E item;
            Node next;

            Node(E item) {
                this.item = item;
            }
        }
    }

    public static void main(String[] args) {
        Model model = new OptimizedLockConditionMode(3);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
    }
}
