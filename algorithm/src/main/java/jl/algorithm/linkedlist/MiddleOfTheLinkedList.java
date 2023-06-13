package jl.algorithm.linkedlist;

/**
 * 链表的中间节点
 * 快慢指针，快指针一次走两步，慢指针一次走一步
 *
 * @author Liu Chang
 * @date 2021/3/15
 */
public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        if (head == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
