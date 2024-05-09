package P1_100.P1_10;

import org.junit.Test;

import java.util.HashMap;

//给定一个字符串s，请你找出其中不含有重复字符的最长子串的长度。
public class P3 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] charArr = s.toCharArray();
        int[] dp = new int[s.length()];//记录以每个字符作为结尾的子串其不含有重复字符的子串长度
        HashMap<Character, Integer> posMap = new HashMap<>();
        posMap.put(charArr[0], 0);
        dp[0] = 1;
        int len = 1;
        for (int i = 1; i < dp.length; i++) {
            int index = -1;
            if (posMap.containsKey(charArr[i])) {
                index = posMap.get(charArr[i]);//找到前一个与自己相同字符的下标
            }
            posMap.put(charArr[i], i);//实时更新
            dp[i] = Math.min(dp[i - 1] + 1, i - index);//i-1的最大子串长度与前一个重复字符距离，靠自己更近的是瓶颈
            len = Math.max(dp[i], len);
        }
        return len;
    }

    @Test
    public void test() {
        String s = "au";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
