package P1_100.P71_80;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class P77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, k, new boolean[n], 0);
        return res;
    }


    /**
     * @param res 记录总的组合数
     * @param k 当前填写第k位
     * @param used 记录每个位置是否被使用过
     * @param j 从j及其之后的数选择
     */
    public void dfs(List<List<Integer>> res, int k, boolean[] used, int j) {
        if (k == 0) {//k==0 说明选完了k个数
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < used.length; i++) {//记录当前组合
                if (used[i]) {
                    ans.add(i + 1);
                }
            }
            res.add(ans);//添加到总的组合
            return;
        }
        for (int i = j; i < used.length; i++) {//决定第k个数选择哪个数
            if (!used[i]) {//当前数未被选择过
                used[i] = true;//记录被选择过
                dfs(res, k - 1, used, i + 1);//决定 k-1 位置
                used[i] = false;//回溯后当前位置不再选择当前数字 置为false
            }
        }
    }

    @Test
    public void test() {
        List<List<Integer>> combine = combine(4, 2);
        System.out.println(combine);
    }
}
