package jl.algorithm.string;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/3/18
 */
public class StringToIntegerAtoi {

    public int myAtoi(String s) {
        s = s.trim();
        if (s == null || s.equals("")) {
            return 0;
        }
        char sign = s.charAt(0);
        boolean negative = false;
        boolean noSign = false;
        if (sign == '-') {
            negative = true;
        } else if (sign != '+') {
            noSign = true;
        }
        if (!noSign) {
            s = s.substring(1);
        }

        StringBuilder sb = new StringBuilder();
        boolean allZero = true;
        for (char c : s.toCharArray()) {
            if (!allZero) {
                if (c >= 48 && c <= 57) {
                    sb.append(c);
                } else {
                    break;
                }
            } else {
                if (c > 48 && c <= 57) {
                    sb.append(c);
                    allZero = false;
                } else if (c == 48) {
                    continue;
                } else {
                    break;
                }
            }

        }
        String resStr = sb.toString();
        if (resStr.equals("")) {
            return 0;
        }

        if (resStr.length() > 10) {
            if (negative) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        Long res = Long.valueOf(resStr);
        if (negative) {
            res = res * -1;
        }
        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return res.intValue();
    }

    public static void main(String[] args) {
        int i = new StringToIntegerAtoi().myAtoi("  0000000000012345678");
    }

}
