package P1_100.P11_20;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
*/
public class P17 {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        HashMap<Character, ArrayList<Character>> map = new HashMap<>();
        initMap(map);
        List<String> res = new ArrayList<>();
        process(digits.toCharArray(), res, map, 0, new StringBuffer());
        return res;
    }

    public void process(char[] digits, List<String> res,
                        HashMap<Character, ArrayList<Character>> map, int i, StringBuffer str) {
        if (i == digits.length) {
            res.add(str.toString());
            return;
        }
        ArrayList<Character> list = map.get(digits[i]);
        for (char cur : list) {
            process(digits, res, map, i + 1, str.append(cur));
            str.deleteCharAt(str.length() - 1);
        }
    }

    public void initMap(HashMap<Character, ArrayList<Character>> map) {
        int j = 0;
        for (int i = 2; i <= 9; i++) {
            ArrayList<Character> list = new ArrayList<>();
            int k = (i == 7 || i == 9) ? 4 : 3;
            for (int l = 0; l < k; l++) {
                list.add((char) ('a' + j++));
            }
            map.put((char) (i + '0'), list);
        }
    }

    @Test
    public void test() {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }
}
