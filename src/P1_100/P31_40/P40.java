package P1_100.P31_40;

import org.junit.Test;

import java.util.*;

public class P40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);//升序排列
        backtrace(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    /**
     * 组合数可以想象成树形结构，每一层代表组合数的第i个数，
     * 下一层只能从i之后的数选取（避免了重复的组合，要求给定数组中元素不重复）
     * 将数组排序，重复的元素同层只选取第一个，后续相同的数字同层不再选取，
     * 但下一层可能会选取与本层相同的数（也就是同层不重复，树枝可以重复）
     */
    public void backtrace(List<List<Integer>> res, List<Integer> temp, int[] candidates,
                          int target, int begin) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            if (target < candidates[i]) {//如果当前数比target大
                // 由于升序排列 后续数也一定会大于target
                return;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {//同一层不重复选
                continue;
            }
            temp.add(candidates[i]);//本层选取该数
            backtrace(res, temp, candidates, target - candidates[i], i + 1);
            temp.remove(temp.size() - 1);//本层移除当前数字
        }
    }

    @Test
    public void testConvert() {
        String str = "[10,1,2,7,6,1,5]";
        str = str.replace('[', '{');
        str = str.replace(']', '}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> lists = combinationSum2(candidates, target);
        System.out.println(lists);
    }
}
