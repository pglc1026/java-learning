package jl.structure.queue;

/**
 * 循环队列
 *
 * @author Liu Chang
 * @date 2021/3/23
 */
public class CycleQueue {

    private String[] items;

    private int head;

    private int tail;

    private int n;

    public CycleQueue(int capacity) {
        n = capacity;
        items = new String[capacity];
        head = 0;
        tail = 0;
    }

    public boolean enqueue(String item) {
        if ((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        if (head == tail) return null;
        String item = items[head];
        head = (head + 1) % n;
        return item;
    }

    public static void main(String[] args) {
        CycleQueue queue = new CycleQueue(5);
        System.out.println(queue.enqueue("1"));
        System.out.println(queue.enqueue("2"));
        System.out.println(queue.enqueue("3"));
        System.out.println(queue.enqueue("4"));
        System.out.println(queue.enqueue("5"));
        System.out.println(queue.enqueue("6"));
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.enqueue("1"));
        System.out.println(queue.enqueue("2"));
        System.out.println(queue.enqueue("3"));
        System.out.println(queue.enqueue("4"));
        System.out.println(queue.enqueue("5"));
    }
}
