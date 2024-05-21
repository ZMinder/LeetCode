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
        for (int i = 0; i < s.length(); i++) {
            backtrace(s, i);
        }
        return res;
    }

    /**
     * 当前字符串str可以分割成cuts + 1个子串，cuts相当于分割的次数，在当前状态下，求解分割方案
     */
    public void backtrace(String str, int cuts) {
        if (cuts == 0) {//说明已经分割完成
            //检查最后一个子串是否是回文子串
            if (check(str)) {
                temp.add(str);//添加进当前分割方案
                res.add(new ArrayList<>(temp));//添加进分割方案记录集合
                temp.remove(temp.size() - 1);//移除当前分割方案
            }
            return;
        }
        for (int i = 0; i < str.length() - cuts; i++) {//至少剩余的子串 每个字符可以单独分割成一个子串
            String sub = str.substring(0, i + 1);//当前分割的第一个子串
            if (check(sub)) {//第一个子串是回文子串，继续分割
                temp.add(sub);//添加进当前分割方案
                backtrace(str.substring(i + 1), cuts - 1);
                temp.remove(temp.size() - 1);//移除当前分割子串
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
        String str = "a";
        List<List<String>> partition = partition(str);
        System.out.println(partition);
    }
}
