package P501_600.P501_510;

import org.junit.Test;

/**
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 */
public class P509 {
    public int fib(int n) {
        if (n == 0 || n == 1) {//如果是前两项，直接返回
            return n;
        }
        int f0 = 0;
        int f1 = 1;
        for (int i = 2; i <= n; i++) {//f0和f1始终是第i项的前两项
            int temp = f1;
            f1 += f0;//f1 --> f0 + f1
            f0 = temp;//f0 --> f1
        }
        return f1;
    }

    @Test
    public void test() {
        for (int i = 0; i < 30; i++) {
            System.out.println(fib(i));
        }
    }
}
