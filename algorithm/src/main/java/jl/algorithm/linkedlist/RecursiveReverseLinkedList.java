package jl.algorithm.linkedlist;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * RecursiveReverseLinkedList
 * 单链表反转递归实现
 *
 * @author pglc1026
 * @date 2019-06-26
 */
public class RecursiveReverseLinkedList {

    private static LinkedNode head;

    static {
        head = new LinkedNode(1);
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
    }

    private LinkedNode reverseLinkedList(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        LinkedNode newHead = new RecursiveReverseLinkedList().reverseLinkedList(head);

        LinkedNode pre = newHead;
        while (pre != null) {
            System.out.println(pre.val);
            pre = pre.next;
        }
    }

}
