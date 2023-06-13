package jl.algorithm.linkedlist;

/**
 * PalindromeLinkedList
 *
 * @author pglc1026
 * @date 2019-06-25
 */
public class PalindromeLinkedList {

    private static LinkedNode head;

    static {
        head = new LinkedNode(1);
        LinkedNode linkedNode2 = new LinkedNode(2);
        LinkedNode linkedNode3 = new LinkedNode(3);
        LinkedNode linkedNode4 = new LinkedNode(3);
        LinkedNode linkedNode5 = new LinkedNode(2);
        LinkedNode linkedNode6 = new LinkedNode(1);

        head.next = linkedNode2;
        head.next.next = linkedNode3;
        head.next.next.next = linkedNode4;
        head.next.next.next.next = linkedNode5;
        head.next.next.next.next.next = linkedNode6;
    }

    public static void main(String[] args) {
        if (head == null || head.next == null) {
            System.out.println("true");
            return;
        }

        LinkedNode pre = null;
        LinkedNode slow = head;
        LinkedNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            LinkedNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.val != pre.val) {
                System.out.println(false);
                return;
            }
            slow = slow.next;
            pre = pre.next;
        }

        System.out.println("true");
    }

}
