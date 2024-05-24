package P401_500.P491_500;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。
 * 你可以按 任意顺序 返回答案。
 * <p>
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 */
public class P491 {
    List<List<Integer>> res = new ArrayList<>();//递增子序列的结果集
    List<Integer> temp = new ArrayList<>();//记录当前递增子序列

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrace(nums, 0, Integer.MIN_VALUE);
        return res;
    }

    /**
     * 每个位置的数都可能被选取，选取的要求是大于等于pre
     */
    public void backtrace(int[] nums, int begin, int pre) {
        if (begin == nums.length) {//防止下标越界
            if (temp.size() > 1) {//只要当前递增子序列至少存在两个元素 就加入结果集
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        if (nums[begin] >= pre) {//大于等于前一个数
            temp.add(nums[begin]);//当前数加入递增子序列
            backtrace(nums, begin + 1, nums[begin]);//确定数组下一个数能否进入子序列中
            temp.remove(temp.size() - 1);//当前数不加入子序列
        }
        if (nums[begin] != pre) {//只有当相邻两个数相等时，才可能出现递增子序列重复问题
            //此时存在三种情况：1.两个数同时选取
            //2.两个数都不选取
            //3.只选取一个（这种情况会出现重复）
            // nums[begin] != pre 使得只选取后一个
            backtrace(nums, begin + 1, pre);//递增子序列不考虑当前数
        }
    }

    @Test
    public void test() {
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> subsequences = findSubsequences(nums);
        System.out.println("subsequences = " + subsequences);
    }
}
