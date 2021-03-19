package jl.algorithm.linkedlist;

/**
 * 判断一个单链表中是否有环
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 定义快慢指针，如果快慢指针相遇，则说明有环
 *
 * @author Liu Chang
 * @date 2021/3/15
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode s = head;
        ListNode f = head.next;
        while (f != null && f.next != null) {
            if (f == s) {
                return true;
            }
            s = s.next;
            f = f.next.next;
        }
        return false;
    }

}
