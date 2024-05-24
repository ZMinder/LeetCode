package P401_500.P491_500;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
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
     * 将子序列想象为一棵树，第i层是子序列的第i个数，同层的数不能重复，同一树枝的数可以重复
     * 第i层的数选取范围在begin~nums.length-1，且必须大于等于pre，且同层选择的数不可重复
     * 假设本层选取下标为j的数，i+1层选取范围要在j+1~nums.length-1内
     * 如何确保本层选取的数不重复，使用HashSet记录已经选取的数，如果不在HashSet内代表可以选取
     */
    public void backtrace(int[] nums, int begin, int pre) {
        HashSet<Integer> used = new HashSet<>();//记录当前子序列位置的可选值是否使用过
        for (int i = begin; i < nums.length; i++) {//当前子序列某个位置可选值
            if (nums[i] >= pre && !used.contains(nums[i])) {
                //如果大于等于子序列的前一个数并且该数在子序列的当前位置未使用过
                temp.add(nums[i]);//当前数加入递增子序列
                used.add(nums[i]);//标记为使用过
                if (temp.size() > 1) {//只要当前递增子序列至少存在两个元素 就加入结果集
                    res.add(new ArrayList<>(temp));
                }
                backtrace(nums, i + 1, nums[i]);//确定数组下一个数能否进入子序列中
                temp.remove(temp.size() - 1);//当前数不加入子序列
            }
        }
    }

    @Test
    public void test() {
        int[] nums = {4, 4, 3, 2, 1};
        List<List<Integer>> subsequences = findSubsequences(nums);
        System.out.println("subsequences = " + subsequences);
    }
}
