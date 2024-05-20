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
     * 一层一层的来选，第一层是组合的第一个数，第二层是组合的第二个数
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
