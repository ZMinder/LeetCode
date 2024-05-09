package P1_100.P21_30;

import org.junit.Test;

/*
给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标
（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class P28 {
    public int strStr(String haystack, String needle) {
        int p1 = 0;
        int p2 = 0;
        int[] next = getNext(needle);
        while (p1 < haystack.length() && p2 < needle.length()) {
            if (haystack.charAt(p1) == needle.charAt(p2)) {
                p1++;
                p2++;
            } else if (p2 != 0) {
                p2 = next[p2];
            } else {
                p1++;
            }
        }
        return p2 == needle.length() ? p1 - p2 : -1;
    }

    public int[] getNext(String str) {
        int[] next = new int[str.length()];//记录i之前字符串前缀跟后缀最长匹配长度
        int index = 2;
        int ret = 0;
        while (index < str.length()) {
            if (str.charAt(index - 1) == str.charAt(ret)) {
                next[index++] = ++ret;
            } else if (ret != 0) {
                ret = next[ret];
            } else {
                index++;
            }
        }
        return next;
    }

    @Test
    public void test() {
        String str1 = "aabaaabaaac";
        String str2 = "aabaaac";
        int ret = strStr(str1, str2);
        System.out.println(ret);
    }
}
