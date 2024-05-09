package P1_100.P31_40;

import org.junit.Test;

//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
public class P32 {
    public int longestValidParentheses(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int[] dp = new int[str.length()];//记录以每个位置作为结尾其最长有效子串长度
        int maxLen = Integer.MIN_VALUE;
        //0 位置只有一个字符，不可能凑成括号
        for (int i = 1; i < str.length(); i++) {
            //以左括号结尾长度为0
            if (str.charAt(i) == ')') {//以右括号结尾，需要考虑 i-1 位置的最大长度的前一个字符是不是左括号
                //如果是左括号，则是i-1的最大长度+2，再加上pre前一个字符最大长度
                int pre = i - 1 - dp[i - 1];//pre不能越界
                if (pre >= 0 && str.charAt(pre) == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    @Test
    public void test() {
        System.out.println(longestValidParentheses("()()"));
    }
}
