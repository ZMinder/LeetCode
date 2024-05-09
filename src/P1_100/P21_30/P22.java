package P1_100.P21_30;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
public class P22 {
    public ArrayList<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        process(new StringBuffer(), n, n);
        return res;
    }

    //left 左括号的数量 right 右括号的数量 str的每个位置可以放置左括号或者右括号
    public void process(StringBuffer str, int left, int right) {
        if (left < 0 || left > right) {//右括号数量不得大于左括号数量
            return;
        }
        if (left == 0 && right == 0) {
            res.add(str.toString());
            return;
        }
        process(str.append('('), left - 1, right);
        str.deleteCharAt(str.length() - 1);
        process(str.append(')'), left, right - 1);
        str.deleteCharAt(str.length() - 1);
    }
    @Test
    public void test()
    {
        generateParenthesis(1);
        System.out.println(res);
    }
}
