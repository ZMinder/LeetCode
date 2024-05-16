package P1001_1100.P1011_1020;

import org.junit.Test;

public class P1017 {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        while (n != 0) {
            int rest = n & 1;
            res.append(rest);
            n -= rest;
            n /= -2;
        }
        return res.reverse().toString();
    }

    @Test
    public void test() {
        System.out.println(baseNeg2(2));
        System.out.println(baseNeg2(3));
        System.out.println(baseNeg2(4));
    }
}
