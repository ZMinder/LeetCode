package P1_100.P21_30;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
例如，如果 words = ["ab","cd","ef"]，
那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
"acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 */
public class P30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int len = words[0].length();
        int num = words.length;
        HashMap<String, Integer> allWords = new HashMap<>();
        for (String str : words) {//words中字符串的词频统计表
            int value = allWords.getOrDefault(str, 0);
            allWords.put(str, value + 1);
        }
        for (int i = 0; i < len; i++) {//所有长度为length = len*num的窗口都可以简化为从i~len-1开始，长度为length 依次向右移len
            HashMap<String, Integer> hasWords = new HashMap<>();
            int wordNum = 0;
            //j是窗口左边界
            for (int j = i; j < s.length() - len * num + 1; j += len) {
                while (wordNum < num) {
                    String word = s.substring(j + wordNum * len, j + (wordNum + 1) * len);
                    if (!allWords.containsKey(word)) {//此字符串不存在于words中
                        hasWords.clear();
                        j += wordNum * len;
                        wordNum = 0;//清空窗口中字符串数量
                        break;
                    }
                    int value = hasWords.getOrDefault(word, 0);//获取当前窗口内此字符串数量
                    hasWords.put(word, value + 1);
                    if (hasWords.get(word) > allWords.get(word)) {
                        //该字符串出现次数超过words中有的个数
                        int removed = 0;
                        while (hasWords.get(word) > allWords.get(word)) {//不断移出第一个字符串
                            String first = s.substring(j + removed * len, j + (removed + 1) * len);
                            int firstValue = hasWords.get(first);
                            hasWords.put(first, firstValue - 1);
                            removed++;
                        }
                        wordNum = wordNum - removed + 1;
                        j += (removed - 1) * len;
                        break;
                    }
                    wordNum++;
                }
                if (wordNum == num) {//当前窗口符合规则 移出当前窗口第一个字符串
                    list.add(j);
                    String first = s.substring(j, j + len);
                    int firstValue = hasWords.get(first);
                    hasWords.put(first, firstValue - 1);
                    wordNum--;
                }
            }
        }
        return list;
    }


    //全排列超时 - 未利用到words中字符串长度相等的特性
    @Test
    public void test() {
        String s = "barfoothefoobarman";
        String[] words = new String[]{"bar", "foo"};
        List<Integer> list = findSubstring(s, words);
        System.out.println(list);
    }

}
