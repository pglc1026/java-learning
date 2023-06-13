package jl.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author Liu Chang
 * @date 2021/4/9
 */
public class SelectionSort {

    public void selectionSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n; i++) {
            int min = a[i];
            int pos = i;
            for (int j = i + 1; j < n; j++) {
                if (min > a[j]) {
                    min = a[j];
                    pos = j;
                }
            }
            if (i != pos) {
                // 交换 a[i] 和 a[pos] 的位置
                int temp = a[i];
                a[i] = a[pos];
                a[pos] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 6, 5, 2, 3, 1};
        new SelectionSort().selectionSort(a, 6);
        System.out.println(Arrays.toString(a));
    }

}
