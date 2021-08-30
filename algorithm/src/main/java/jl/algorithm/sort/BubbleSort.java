package jl.algorithm.sort;

import java.util.Arrays;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/4/9
 */
public class BubbleSort {

    public void bubbleSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    // 交换
                    a[j] = a[j] ^ a[j + 1];
                    a[j + 1] = a[j] ^ a[j + 1];
                    a[j] = a[j + 1] ^ a[j];
                    flag = true;
                }
            }
            if (!flag) break;
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 6, 5, 2, 3, 1};
        new BubbleSort().bubbleSort(a, 6);
        System.out.println(Arrays.toString(a));
    }

}
