package P1_100.P31_40;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class P39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    /**
     * 从i位置开始，每个位置的数可以使用多个，凑出target
     *
     * @param res        存储结果集的集合
     * @param temp       存储当前使用的数字
     * @param candidates 原始数据数组
     * @param target     要凑到的数
     * @param i          当前candidates下标位置
     */
    public void backtrace(List<List<Integer>> res, List<Integer> temp,
                          int[] candidates, int target, int i) {
        if (target == 0) {//说明当前凑到了target 先判断是否凑到 再判断下标越界 可能使用最后一个元素凑到了target
            res.add(new ArrayList<>(temp));
            return;
        }
        if(i == candidates.length){//防止i越界
            return;
        }
        for (int j = 0; candidates[i] * j <= target; j++) {//i位置的数选取多个，但不超过target
            for (int k = 0; k < j; k++) {
                temp.add(candidates[i]);//添加j个candidates[i]
            }
            backtrace(res, temp, candidates, target - j * candidates[i], i + 1);
            for (int k = 0; k < j; k++) {
                temp.remove(temp.size() - 1);//移除j个candidates[i]
            }
        }
    }

    @Test
    public void testConvert() {
        String str = "[2,3,6,7]";
        str = str.replace('[', '{');
        str = str.replace(']', '}');
        System.out.println(str);
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> lists = combinationSum(arr, target);
        System.out.println(lists);
    }
}
