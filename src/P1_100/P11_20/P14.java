package P1_100.P11_20;

import org.junit.Test;

/*
编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。
*/
public class P14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = strs[0];//默认以第一个字符串作为前缀
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(pre) != 0) {//如果pre是strs[i]的前缀，返回值应该是0
                //如果返回值不是0，则意味着pre不是其前缀，将pre截断继续尝试
                pre = pre.substring(0, pre.length() - 1);
            }
            if (pre == "") {//已经无公共前缀了，没必要继续比较
                break;
            }
        }
        return pre;
    }

    @Test
    public void test() {
        String[] strs = {"aaa", "aa", "aaa"};
        System.out.println(longestCommonPrefix(strs));
    }
}
