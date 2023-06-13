package jl.algorithm.linkedlist;

import static jl.algorithm.linkedlist.LinkedNode.init;
import static jl.algorithm.linkedlist.LinkedNode.initLoop;

/**
 * DetectLoop
 *
 * @author pglc1026
 * @date 2019-06-27
 */
public class DetectLoop {

    private static boolean detectLoop(LinkedNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        LinkedNode fast = head;
        LinkedNode slow = head;

        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == null || fast.next == null) {
                return false;
            } else if (fast == slow) {
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        LinkedNode head = init();
        boolean b = detectLoop(head);
        System.out.println(b);

        LinkedNode loop = initLoop();
        boolean b1 = detectLoop(loop);
        System.out.println(b1);
    }

}
