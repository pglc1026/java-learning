package jl.algorithm.reverse;

import java.util.Stack;

/**
 * 整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author Liu Chang
 * @date 2021/3/17
 */
public class ReverseInteger {

    public int reverse(int x) {
        if (x == Integer.MIN_VALUE) return 0;
        int divisor = 1000000000;
        boolean negative = x < 0;
        Stack<Integer> numStack = new Stack<>();
        int tempX = Math.abs(x);
        int num;
        boolean allZero = true;
        int tempDivisor = divisor;
        for (int i = 9; i >= 0; i--) {
            num = tempX / tempDivisor;
            if (num != 0) {
                allZero = false;
                numStack.push(num);
                tempX -= num * tempDivisor;
            } else {
                if (!allZero) {
                    numStack.push(num);
                    tempX -= num * tempDivisor;
                }
            }
            tempDivisor /= 10;
        }

        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        int maxLen = 10;
        boolean lenIsEq = numStack.size() == maxLen;
        int result = 0;
        tempDivisor = 1;
        for (int i = 0; i < numStack.size() - 1; i++) {
            tempDivisor *= 10;
        }
        int newDivisor = divisor;
        boolean greater = false;
        if (negative) {
            if (lenIsEq) {
                int stackNum;
                int minNum;
                int tempMin = min;
                for (int i = 9; i >= 0; i--) {
                    minNum = Math.abs(tempMin / newDivisor);
                    stackNum = numStack.pop();
                    if (!greater && stackNum > minNum) {
                        return 0;
                    } else if (stackNum == minNum) {
                        result += stackNum * tempDivisor;
                    } else {
                        result += stackNum * tempDivisor;
                        greater = true;
                    }
                    tempMin -= minNum * newDivisor;
                    tempDivisor /= 10;
                    newDivisor /= 10;
                }
                result *= -1;
            } else {
                for (int i = numStack.size(); i > 0; i--) {
                    result += numStack.pop() * tempDivisor;
                    tempDivisor /= 10;
                    newDivisor /= 10;
                }
                result *= -1;
            }
        } else {
            if (lenIsEq) {
                int stackNum;
                int maxNum;
                int tempMax = max;
                for (int i = 9; i >= 0; i--) {
                    maxNum = tempMax / newDivisor;
                    stackNum = numStack.pop();
                    if (!greater && stackNum > maxNum) {
                        return 0;
                    } else if (stackNum == maxNum) {
                        result += stackNum * tempDivisor;
                    } else {
                        result += stackNum * tempDivisor;
                        greater = true;
                    }
                    tempMax -= maxNum * newDivisor;
                    tempDivisor /= 10;
                    newDivisor /= 10;
                }
            } else {
                for (int i = numStack.size(); i > 0; i--) {
                    result += numStack.pop() * tempDivisor;
                    tempDivisor /= 10;
                    newDivisor /= 10;
                }
            }
        }

        return result;
    }

    public int reverse2(int x) {
        int maxPre = Integer.MAX_VALUE / 10;
        int maxEnd = Integer.MAX_VALUE % 10;
        int minPre = Integer.MIN_VALUE / 10;
        int minEnd = Integer.MIN_VALUE % 10;
        int res = 0;
        int tmp = 0;
        while (x != 0) {
            tmp = x % 10;
            if (res > maxPre || (res == maxPre && tmp > maxEnd)) {
                return 0;
            }
            if (res < minPre || (res == minPre && tmp < minEnd)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return x;
    }

}
