package jl.pc.waitnotify;

import jl.pc.common.AbstractConsumer;
import jl.pc.common.AbstractProducer;
import jl.pc.common.Model;
import jl.pc.common.Task;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WatiNotifyModel
 *
 * @author liuchang39
 * @date 2019/3/18
 */
public class WatiNotifyModel implements Model {

    private final int cap;

    private final Object BUFFER_LOCK = new Object();

    private final Queue<Task> buffer = new LinkedList<>();

    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public WatiNotifyModel(int cap) {
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
            synchronized (BUFFER_LOCK) {
                while (buffer.size() == 0) {
                    BUFFER_LOCK.wait();
                }
                Task task = buffer.poll();
                assert task != null;
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume: " + task.getNo());
                BUFFER_LOCK.notifyAll();
            }
        }
    }

    private class ProducerImpl extends AbstractProducer {

        @Override
        public void produce() throws InterruptedException {
            // 不定期生产，模拟随机的用户请求
            Thread.sleep((long) (Math.random() * 1000));
            synchronized (BUFFER_LOCK) {
                while (buffer.size() == cap) {
                    BUFFER_LOCK.wait();
                }
                Task task = new Task(increTaskNo.getAndIncrement());
                buffer.offer(task);
                System.out.println("produce: " + task.getNo());
                BUFFER_LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Model model = new WatiNotifyModel(3);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
    }
}
