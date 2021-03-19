package jl.algorithm.linkedlist;

/**
 * 两数相加 <a href="https://leetcode-cn.com/problems/add-two-numbers/">题目</a>
 *
 * @author Liu Chang
 * @date 2021/3/4
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode();
        ListNode pre = head;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode listNode = new ListNode();
            int l1V = l1 == null ? 0 : l1.val;
            int l2V = l2 == null ? 0 : l2.val;
            int val = l1V + l2V + carry;
            if (val >= 10) {
                val -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            listNode.val = val;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            pre.next = listNode;
            pre = pre.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
//        ListNode l11 = new ListNode(2);
//        ListNode l12 = new ListNode(4);
//        ListNode l13 = new ListNode(3);
//        l11.next = l12;
//        l12.next = l13;
//
//        ListNode l21 = new ListNode(5);
//        ListNode l22 = new ListNode(6);
//        ListNode l23 = new ListNode(4);
//        l21.next = l22;
//        l22.next = l23;

        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));


        System.out.println(new AddTwoNumbers().addTwoNumbers(l1, l2));
    }

}
