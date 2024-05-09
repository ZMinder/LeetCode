package P1_100.P1_10;

import org.junit.Test;

/*
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
*/
public class P10 {
    //动态规划 dp[i][j]的含义为s[...i-1]与p[...j-1]的匹配情况
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;//空串与空串进行比较
        for (int i = 2; i <= pLen; i++) {//s是空串的情况下，与p进行比较
            //因为*前面肯定有一个字符，所以i=1时必定匹配不成功，因此i从2开始
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                //当前字符匹配成功，0~i-1与0~j-1匹配成功与否取决于0~i-2与0~j-2的匹配情况
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    //s当前字符与p中*的前一个字符匹配
                    //此时存在两种选择，一种是抛弃p中的*和前一个字符dp[i][j - 2]
                    //另一种是使用*和前一个字符的组合匹配掉s[i-1]，此时匹配结果取决于0~i-2与0~j-1
                    //两种选择只要有一种成立均为true
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {//s当前字符与p中*的前一个字符不匹配，*只能删除前一个字符
                        //此时匹配结果取决于0~i-1与0~j-3
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[sLen][pLen];
    }

    @Test
    public void test() {
        System.out.println(isMatch("aa", "a*"));
    }
}
