package P401_500.P451_460;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
 * 都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，
 * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 */
public class P455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < s.length; i++) {
            heap.offer(s[i]);
        }
        int res = 0;//记录满足孩子的数量
        for (int i = 0; i < g.length; i++) {
            int appetite = g[i];
            while (!heap.isEmpty() && heap.peek() < appetite) {//找到一个大于等于胃口的饼干
                heap.poll();
            }
            if (!heap.isEmpty()) {//找到了
                res++;
                heap.poll();
            } else {//找不到
                break;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] g = {1, 2};
        int[] s = {1, 2, 3};
        int res = findContentChildren(g, s);
        System.out.println(res);
    }
}
