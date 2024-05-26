package P1_100.P41_50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class P47 {
    List<List<Integer>> res = new ArrayList<>();//存储所有全排列的集合

    public List<List<Integer>> permuteUnique(int[] nums) {
        backtrace(nums, 0);
        return res;
    }

    /**
     * index表示全排列第i个位置，也是全排列数的第i层
     */
    public void backtrace(int[] nums, int index) {
        if (index == nums.length) {
            List<Integer> temp = new ArrayList<>();//存储当前全排列
            for (int i = 0; i < nums.length; i++) {
                temp.add(nums[i]);
            }
            res.add(temp);
        }
        HashSet used = new HashSet();//存储本层使用过的数字
        for (int i = index; i < nums.length; i++) {
            if (!used.contains(nums[i])) {
                used.add(nums[i]);//标记为本层已使用
                swap(nums, index, i);//确定index位置的数
                backtrace(nums, index + 1);//确定index+1位置
                swap(nums, index, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {//交换数组中两个数的值
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 2};
        List<List<Integer>> lists = permuteUnique(nums);
        System.out.println(lists);
    }
}
