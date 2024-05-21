package P101_200.P131_140;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。返回 s 所有可能的分割方案。
 */
public class P131 {
    List<List<String>> res = new ArrayList<>();//存储分割方案的结果集
    List<String> temp = new ArrayList<>();//存储当前分割方案

    public List<List<String>> partition(String s) {
        boolean[][] checked = generate(s);
        backtrace(s, checked, 0);
        return res;
    }

    public boolean[][] generate(String str) {
        //记录str的子串[i,j)是否是回文子串
        boolean[][] checked = new boolean[str.length()][str.length()];
        //使用动态规划的方式判断是否为回文子串
        for (int i = 0; i < str.length(); i++) {//预处理i>=j的情况 全部置为true
            for (int j = 0; j <= i; j++) {
                checked[i][j] = true;
            }
        }
        //自下而上 自左而右
        for (int i = str.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {//当首尾相同，取决于i+1~j-1
                    //当j-i==1时，就利用到了预处理i>=j的情况 全部置为true
                    checked[i][j] = checked[i + 1][j - 1];
                }
            }
        }
        return checked;
    }

    //考虑前几个字符能够构成分割方案的第一个字符串，如果可以，产生一个分割方案
    //同时在后续字符串以同样的方式考虑后续分割方案
    public void backtrace(String str, boolean[][] checked, int begin) {
        if (begin == str.length()) {//说明分割结束，产生了一个分割方案
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = begin; i < str.length(); i++) {//考虑前i+1个字符能否构成回文子串
            String sub = str.substring(begin, i + 1);
            if (checked[begin][i]) {//如果前i+1个字符构成回文字符串
                temp.add(sub);
                backtrace(str, checked, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public boolean check(String str) {//判断当前字符串是否为回文字符串
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        String str = "cbbbcc";
        List<List<String>> partition = partition(str);
        System.out.println(partition);
    }
}
