package jl.algorithm.linkedlist;

/**
 * LinkedNode
 *
 * @author pglc1026
 * @date 2019-06-26
 */
public class LinkedNode {
    int val;

    LinkedNode next = null;

    public LinkedNode(int val) {
        this.val = val;
    }

    public static LinkedNode init() {
        LinkedNode head = new LinkedNode(1);
        LinkedNode linkedNode2 = new LinkedNode(2);
        LinkedNode linkedNode3 = new LinkedNode(3);
        LinkedNode linkedNode4 = new LinkedNode(4);
        LinkedNode linkedNode5 = new LinkedNode(5);
        LinkedNode linkedNode6 = new LinkedNode(6);

        head.next = linkedNode2;
        head.next.next = linkedNode3;
        head.next.next.next = linkedNode4;
        head.next.next.next.next = linkedNode5;
        head.next.next.next.next.next = linkedNode6;
        return head;
    }

    public static LinkedNode initLoop() {
        LinkedNode head = new LinkedNode(1);
        LinkedNode linkedNode2 = new LinkedNode(2);
        LinkedNode linkedNode3 = new LinkedNode(3);
        LinkedNode linkedNode4 = new LinkedNode(4);
        LinkedNode linkedNode5 = new LinkedNode(5);
        LinkedNode linkedNode6 = new LinkedNode(6);

        head.next = linkedNode2;
        head.next.next = linkedNode3;
        head.next.next.next = linkedNode4;
        head.next.next.next.next = linkedNode5;
        head.next.next.next.next.next = linkedNode6;
        head.next.next.next.next.next.next =  linkedNode3;
        return head;
    }

}
