package jl.algorithm.array;

/**
 * 寻找两个正序数组的中位数 <a href="https://leetcode-cn.com/problems/median-of-two-sorted-arrays/">题目</a>
 *
 * @author Liu Chang
 * @date 2021/3/4
 */
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int m = (length1 + length2) % 2;
        int middle = (length1 + length2) / 2;
        if (middle < 1) {
            return length1 > length2 ? nums1[0] : nums2[0];
        }

        int[] nums = new int[middle + 1];
        int i = 0;
        int j = 0;
        int k = 0;
        for (;i + j < middle + 1; k++) {
            if (i == length1) {
                nums[k] = nums2[j];
                j++;
                continue;
            }
            if (j == length2) {
                nums[k] = nums1[i];
                i++;
                continue;
            }

            if (nums1[i] > nums2[j]) {
                nums[k] = nums2[j];
                j++;
            } else {
                nums[k] = nums1[i];
                i++;
            }
        }
        if (m != 0) {
            return nums[nums.length - 1] * 1.0;
        } else {
            return (nums[nums.length - 1] + nums[nums.length - 2]) / 2.0;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
//        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(new FindMedianSortedArrays().findMedianSortedArrays(new int[]{}, new int[]{2, 3}));
    }

}
