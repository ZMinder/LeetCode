package P1_100.P1_10;

import org.junit.Test;

/*
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
请你实现这个将字符串进行指定行数变换的函数：
*/
public class P6 {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows < 1) {
            return "";
        }
        if (numRows == 1) {
            return s;
        }
        int gap = 2 * (numRows - 1);
        StringBuilder res = new StringBuilder(s.length());
        for (int i = 0; i < numRows; i++) {
            int start = i;
            while (start < s.length()) {
                res.append(s.charAt(start));
                if (i != 0 && i != numRows - 1 && start + gap < s.length()) {
                    res.append(s.charAt(start + gap));
                }
                start += 2 * (numRows - 1);
            }
            gap -= 2;
        }
        return res.toString();
    }

    @Test
    public void test() {
        System.out.println(convert("PAYPALISHIRING", 4));
    }
}
