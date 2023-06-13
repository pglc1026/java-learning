package jl.algorithm.stack;

import java.util.Stack;

/**
 * Calculator
 *
 * @author pglc1026
 * @date 2019-07-08
 */
public class Calculator {

    public int calculate(String s) {
        int sum = 0;
        if (s == null || s.length() == 0) {
            return sum;
        }
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        // 遍历
        char singleChar;
        // 加减号数量
        int opCount = 0;
        for (int i = 0; i < s.length(); i++) {
            singleChar = s.charAt(i);
            if (singleChar == '+' || singleChar == '-') {
                opStack.push(singleChar);
                opCount++;
            } else if (singleChar == '(') {
                opStack.push(singleChar);
            } else if (singleChar == ')') {
                // 开始计算
                int num = 0;
                int op;
                while (opStack.peek() != '(') {
                    op = opStack.pop() == '+' ? 1 : -1;
                    num += numStack.pop() * op;
                    opCount--;
                }
                // ( 弹栈
                opStack.pop();
                num += numStack.pop();
                // 结果压栈
                numStack.push(num);
            } else if (singleChar != ' ') {
                // 数字
                // 如果数字栈的元素个数大于操作符栈元素个数，那么该数字应该与栈顶数字继续组合
                if (numStack.size() > opCount) {
                    numStack.push(numStack.pop() * 10 + singleChar - '0');
                } else {
                    // 直接入栈
                    numStack.push(singleChar - '0');
                }
            }
        }

        // 以操作符栈为基准遍历两个栈
        while (!opStack.empty()) {
            sum += numStack.pop() * (opStack.pop() == '+' ? 1 : -1);
        }
        // 把最后一个数字加上
        sum += numStack.pop();
        return sum;
    }

    public static void main(String[] args) {
        String s = "2-4-(8+2-6+(8+4-(1)+8-10))";
        int res = new Calculator().calculate(s);
        System.out.println(res);
    }

}
