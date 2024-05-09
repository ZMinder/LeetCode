package P1_100.P41_50;

import org.junit.Test;

/*
给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符序列（包括空字符序列）。
判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
*/
public class P44 {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];//s[0...i-1]与p[0...j-1]匹配情况
        dp[0][0] = true;
        for (int i = 1; i <= pLen; i++) {//处理第一行 s="" 与p的匹配情况
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    //当前字符匹配成功，整个子串的匹配情况取决于dp[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {//'*'可以匹配任意长度的字符序列
                    //'*'匹配"" 或者 多个字母（i--直到与*前面字符匹配）
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[sLen][pLen];
    }

    @Test
    public void test() {
        String s = "ab";
        String p = "ac*";
        System.out.println(isMatch(s, p));
    }
}
