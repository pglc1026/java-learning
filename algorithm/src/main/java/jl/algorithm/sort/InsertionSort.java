package jl.algorithm.sort;

import java.util.Arrays;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/4/9
 */
public class InsertionSort {

    public void insertionSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 1; i < n; i++) {
            // 要插入左边有序区间的值
            int value = a[i];
            int j = i - 1;
            // 找到要插入的位置
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    // 移动数据
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            // 插入元素
            a[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 6, 5, 2, 3, 1};
        new InsertionSort().insertionSort(a, 6);
        System.out.println(Arrays.toString(a));
    }

}
