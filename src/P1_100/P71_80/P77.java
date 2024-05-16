package P1_100.P71_80;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class P77 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return res;
    }

    public void dfs(int cur, int n, int k) {//每个数都有选和不选两种可能
        if (temp.size() + n - cur + 1 < k) {//剩下的数凑不到k个数
            return;
        }
        if (temp.size() == k) {//选择了k个数
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(cur);//选择了当前的数
        dfs(cur + 1, n, k);
        temp.remove(temp.size() - 1);//不选当前的数
        dfs(cur + 1, n, k);
    }

    @Test
    public void test() {
        List<List<Integer>> combine = combine(4, 2);
        System.out.println(combine);
    }
}
