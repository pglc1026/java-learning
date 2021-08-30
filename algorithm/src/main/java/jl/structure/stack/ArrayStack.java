package jl.structure.stack;

/**
 * 数组实现栈
 *
 * @author Liu Chang
 * @date 2021/3/19
 */
public class ArrayStack {

    private String[] items;

    /**
     * 栈大小
     */
    private int n;

    /**
     * 栈中元素个数
     */
    private int count;

    public ArrayStack(int capacity) {
        items = new String[capacity];
        n = capacity;
        count = 0;
    }

    public boolean push(String item) {
        if (count == n) return false;
        items[count++] = item;
        return true;
    }

    public String pop() {
        if (count == 0) return null;
        return items[--count];
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        System.out.println(arrayStack.push("1"));
        System.out.println(arrayStack.push("2"));
        System.out.println(arrayStack.push("3"));
        System.out.println(arrayStack.push("4"));
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }


}
