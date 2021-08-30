package jl.structure.queue;

/**
 * 数组实现的队列
 *
 * @author Liu Chang
 * @date 2021/3/19
 */
public class ArrayQueue {

    private String[] items;

    private int n;

    private int head;

    private int tail;

    public ArrayQueue(int capacity) {
        items = new String[capacity];
        this.n = capacity;
        head = 0;
        tail = 0;
    }

    public boolean enqueue(String item) {
        if (tail == n) {
            if (head == 0) {
                // tail == n 并且 head == 0 表示队列满了
                return false;
            }
            // tail == n 但是队列没有满，进行数据迁移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail++] = item;
        return true;
    }

    public String dequeue() {
        if (head == tail) return null;
        return items[head++];
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        System.out.println(queue.enqueue("0"));
        System.out.println(queue.enqueue("1"));
        System.out.println(queue.enqueue("2"));
        System.out.println(queue.enqueue("3"));
        System.out.println(queue.enqueue("4"));
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.enqueue("5"));
        System.out.println(queue.enqueue("6"));
        System.out.println(queue.enqueue("7"));
        System.out.println(queue.enqueue("8"));
    }

}
