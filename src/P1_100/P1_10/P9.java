package P1_100.P1_10;

import org.junit.Test;

/*
给你一个整数x，如果x是一个回文整数，返回true；否则，返回false。
回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
*/
public class P9 {
    public boolean isPalindrome(int x) {
        return isReverse(String.valueOf(x));
    }

    public static boolean isReverse(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    @Test
    public void test()
    {
        System.out.println(isPalindrome(1112));
    }
}
