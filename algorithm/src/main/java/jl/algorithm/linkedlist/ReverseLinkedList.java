package jl.algorithm.linkedlist;

import static jl.algorithm.linkedlist.LinkedNode.init;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * PalindromeLinkedList
 * 单链表反转一般实现
 *
 * @author pglc1026
 * @date 2019-06-25
 */
public class ReverseLinkedList {

    private static LinkedNode reverse1(LinkedNode head) {
        if (head != null && head.next != null) {
            LinkedNode pre = null;
            LinkedNode cur = head;
            LinkedNode next;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        } else {
            return head;
        }
    }

    private static LinkedNode reverse2(LinkedNode head) {
        // 记录原始的第2个节点
        LinkedNode p = head.next;
        LinkedNode q;
        while (p.next != null) {
            // 第2个节点后的节点
            q = p.next;
            // 相邻的下个节点断开，指向下下个节点
            p.next = q.next;
            // 与相邻节点断开，指向head的下个相邻节点
            q.next = head.next;
            head.next = q;
        }

        // 先收尾相接成还
        p.next = head;
        // 新head
        head = head.next;
        // 原head的next指向null，断开环
        p.next.next = null;
        return head;
    }

    /**
     * 2021-03-15 重做
     */
    private static ListNode reverse3(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        ListNode temp = null;
        while (curr != null && curr.next != null) {
            temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        if (curr != null) {
            curr.next = pre;
        }
        return curr;
    }

    public static void main(String[] args) {
        // 方法1
        System.err.println("============== 方法1 ==============");
        LinkedNode head = init();
        head = reverse1(head);
        printLinkedNode(head);
        System.err.println("============== end ==============");

        // 方法2
        System.err.println("============== 方法2 ==============");
        LinkedNode head2 = init();
        head2 = reverse2(head2);
        printLinkedNode(head2);
        System.err.println("============== end ==============");

    }

    private static void printLinkedNode(LinkedNode head) {
        LinkedNode node = head;
        while (node != null) {
            System.out.print(" ");
            System.out.print(node.val);
            node = node.next;
        }
        System.out.println();
    }

}
