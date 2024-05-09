package P1_100.P11_20;

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
有效字符串需满足：
左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。
*/
public class P20 {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {//括号必须成对
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else if (stack.isEmpty()) {
                return false;
            } else {
                if ((stack.peek() == '(' && s.charAt(i) == ')') ||
                        (stack.peek() == '[' && s.charAt(i) == ']') ||
                        (stack.peek() == '{' && s.charAt(i) == '}')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        System.out.println(isValid("{}[]{)"));
    }
}
