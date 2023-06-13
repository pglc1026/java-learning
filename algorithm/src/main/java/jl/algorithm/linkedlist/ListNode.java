package jl.algorithm.linkedlist;

/**
 * ListNode
 *
 * @author Liu Chang
 * @date 2021/3/4
 */
public class ListNode {

    int val;

    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }

//    @Override
//    public String toString() {
//        StringBuilder res = new StringBuilder(val);
//        ListNode tempNext = next;
//        do {
//            int v = next.val;
//            res.append(v).append(", ");
//            tempNext = tempNext.next;
//        } while (tempNext != null);
//        return res.substring(0, res.length() - 1);
//    }

    public static void main(String[] args) {
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        l11.next = l12;
        l12.next = l13;
        l11.toString();
    }
}
