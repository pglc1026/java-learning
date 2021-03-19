package jl.algorithm.string;

import com.google.common.math.DoubleMath;

import java.util.ArrayList;
import java.util.List;

/**
 * Z 字形变换
 * https://leetcode-cn.com/problems/zigzag-conversion/
 *
 * @author Liu Chang
 * @date 2021/3/16
 */
public class ZigzagConversion {

    public String convert(String s, int numRows) {
        // 使用二维数组
        // 计算最多需要多少列
        int m = numRows == 1 ? 1 : numRows - 1;
        int colNum = numRows == 1 ? s.length() : Double.valueOf(Math.ceil(s.length() * 1.0 / (2 * numRows - 2))).intValue() * m;
        char[][] arr = new char[numRows][colNum];
        int n = 0;
        // 按列遍历二维数组
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < numRows; j++) {
                if (n < s.length()) {
                    if (i % m == 0) {
                        arr[j][i] = s.charAt(n);
                        n++;
                    } else {
                        if ((i + j) % m == 0) {
                            arr[j][i] = s.charAt(n);
                            n++;
                        } else {
                            arr[j][i] = '-';
                        }
                    }
                } else {
                    arr[j][i] = '-';
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < colNum; j++) {
                if (arr[i][j] != '-') {
                    sb.append(arr[i][j]);
                }
            }
        }

        return sb.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, step = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) step = -step;
            i += step;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new ZigzagConversion().convert("PAYPALISHIRING", 3));
        System.out.println(new ZigzagConversion().convert("A", 1));
    }
}
