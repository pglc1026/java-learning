package jl.algorithm.string;

/**
 * 最大回文子串 <a href="">题目</a>
 *
 * @author Liu Chang
 * @date 2021/3/5
 */
public class LongestPalindrome {

    /* ============================== 暴力解法 ============================== */
    /**
     * 暴力解法
     */
    public String longestPalindrome1(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
               String test = s.substring(i, j);
               if (isPalindrome(test) && test.length() > max) {
                   ans = test;
                   max = Math.max(test.length(), max);
               }
            }
        }

        return ans;
    }

    private boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /* ============================== 最长公共子串 ============================== */

    /**
     * 最长公共子串
     */
    public String longestPalindrome2(String s) {
        if (s.equals("")) {
            return "";
        }
        String origin = s;
        // 字符串倒置
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[][] arr = new int[length][length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                if (arr[i][j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[i][j] - 1 == i) {
                        maxLen = arr[i][j];
                        maxEnd = i; // 以 i 位置结尾的字符
                    }
                }

            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /**
     * 暴力破解法优化
     * 定义 p(i, j) = true, 如果 s[i, j] 是回文串
     * 接下来，p(i, j) = p(i + 1, j - 1)&&s[i]==s[j]
     * 即，如果 s[i] == s[j] 并且 s[i + 1, j - 1] 是回文串，那么 s[i, j] 就是回文串
     */
    public String longestPalindrome3(String s) {
        int length = s.length();
        boolean[][] p = new boolean[length][length];
        int maxLen = 0;
        String maxPal = "";
        for (int len = 1; len <= length; len++) {
            for (int start = 0; start < length; start++) {
                int end = start + len - 1;
                if (end >= length) {
                    // 下标已经越界，结束循环
                    break;
                }
                p[start][end] = (len == 1 || len == 2 || p[start + 1][end - 1]) && s.charAt(start) == s.charAt(end);
                if (p[start][end] && len > maxLen) {
                    maxPal = s.substring(start, end + 1);
                    maxLen = len;
                }
            }
        }

        return maxPal;
    }

    /**
     * 暴力破解法优化2
     * 定义 p(i, j) = true, 如果 s[i, j] 是回文串
     * 接下来，p(i, j) = p(i + 1, j - 1)&&s[i]==s[j]
     * 即，如果 s[i] == s[j] 并且 s[i + 1, j - 1] 是回文串，那么 s[i, j] 就是回文串
     *
     * 可以看出, 如果需要知道 p(i, j), 必须先知道 p(i + 1, j - 1), 所以可以倒着遍历，由p(i + 1) 到 p(i)
     */
    public String longestPalindrome4(String s) {
        int length = s.length();
        boolean[][] p = new boolean[length][length];
        String res = "";
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                p[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || p[i + 1][j - 1]);
                if (p[i][j] && j - i + 1> res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    /**
     * 暴力破解法优化3
     * 定义 p(i, j) = true, 如果 s[i, j] 是回文串
     * 接下来，p(i, j) = p(i + 1, j - 1)&&s[i]==s[j]
     * 即，如果 s[i] == s[j] 并且 s[i + 1, j - 1] 是回文串，那么 s[i, j] 就是回文串
     *
     * 可以看出, 如果需要知道 p(i, j), 必须先知道 p(i + 1, j - 1), 所以可以倒着遍历，由p(i + 1) 到 p(i)
     * 同样，可以倒着遍历p(j), 由 p(j - 1) 到 p(j)
     */
    public String longestPalindrome5(String s) {
        int len = s.length();
        boolean[] p = new boolean[len];
        String res = "";
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= i; j--) {
                p[j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || p[j - 1]);
                if (p[j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    /**
     * 中心扩展法
     * 由于字符串的长度可能为奇数或者偶数，所以我们需要从一个字符开始扩展或者从两个字符中间开始扩展
     */
    public String longestPalindrome6(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 从一个字符开始扩展
            int len1 = expandAroundCenter(s, i, i);
            // 从两个字符中间开始扩展
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
//        System.out.println(new LongestPalindrome().longestPalindrome1("a"));
//        System.out.println(new LongestPalindrome().longestPalindrome2("babad"));
//        System.out.println(new LongestPalindrome().longestPalindrome3("babad"));
//        System.out.println(new LongestPalindrome().longestPalindrome4("babad"));
//        System.out.println(new LongestPalindrome().longestPalindrome5("babad"));
        System.out.println(new LongestPalindrome().longestPalindrome6("babcd"));
    }

}
