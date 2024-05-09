package P1_100.P1_10;

import org.junit.Test;

/*
给你一个字符串s，找到s中最长的回文子串。
如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
*/
public class P5 {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        char[] arr = getCharArray(s);
        int[] radius = new int[arr.length];//记录每个位置的最大回文半径
        int center = 0;
        int end = 0;//之前遍历的最大右边界
        int maxLength = Integer.MIN_VALUE;
        int maxPos = -1;
        for (int i = 0; i < arr.length; i++) {
            radius[i] = i < end ? Math.min(radius[2 * center - i], end - i) : 1;
            while ((i - radius[i]) >= 0 && (i + radius[i]) < arr.length) {//向右扩充
                if (arr[i - radius[i]] == arr[i + radius[i]]) {
                    radius[i]++;
                } else {
                    break;
                }
            }
            if (i + radius[i] > end) {//判断右边界是否更新
                end = i + radius[i];
                center = i;
            }
            if (radius[i] > maxLength) {
                maxLength = radius[i];
                maxPos = i;
            }
        }
        int start = (maxPos - (maxLength - 1)) / 2;
        return s.substring(start,start + maxLength - 1);
    }

    public char[] getCharArray(String str) {
        char[] arr = new char[str.length() * 2 + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i % 2 == 0 ? '#' : str.charAt(i / 2);
        }
        return arr;
    }
    @Test
    public void test()
    {
        System.out.println(longestPalindrome("cbbd"));
    }
}
