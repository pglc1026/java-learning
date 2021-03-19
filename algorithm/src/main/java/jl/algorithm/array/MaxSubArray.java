package jl.algorithm.array;

/**
 * 最大连续子数组 <a href="https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/">题目</a>
 *
 * @author Liu Chang
 * @date 2021/3/3
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (temp < 0) {
                temp = nums[i];
            } else {
                temp += nums[i];
            }
            maxSum = Math.max(temp, maxSum);
        }
        return maxSum;
    }

    public int maxSubArray1(int[] nums) {
        int maxSum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int tempSum = nums[i];
            int temp = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                temp += nums[j];
                tempSum = Math.max(temp, tempSum);
            }
            maxSum = Math.max(tempSum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
//        System.out.println(new MaxSubArray().maxSubArray1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//        System.out.println(new MaxSubArray().maxSubArray1(new int[]{-2, 1}));
        System.out.println(new MaxSubArray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(new MaxSubArray().maxSubArray(new int[]{-2, 1}));
    }

}
