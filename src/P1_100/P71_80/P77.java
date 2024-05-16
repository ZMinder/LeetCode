package P1_100.P71_80;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class P77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), k, 1, n);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> ans, int k, int from, int n) {
        if ( n - from + 1 < k) {//剩余的数不够k-size个了
            return;
        }
        if (k == 0) {//k==0 说明选完了k个数
            res.add(new ArrayList<>(ans));//添加到总的组合
            return;
        }
        for (int i = from; i <= n; i++) {//决定从第k个数选择哪个数
            ans.add(i);//添加当前数字
            dfs(res, ans, k - 1, i + 1, n);//决定 k-1 位置
            ans.remove(ans.size() - 1);//移除当前数字
        }
    }

    @Test
    public void test() {
        List<List<Integer>> combine = combine(4, 2);
        System.out.println(combine);
    }
}
