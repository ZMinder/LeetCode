package P1_100.P41_50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
 */
public class P46 {
    List<List<Integer>> res = new ArrayList<>();//存储所有排列的集合
    List<Integer> temp = new ArrayList<>();//存储当前排列的集合

    public List<List<Integer>> permute(int[] nums) {
        backtrace(nums, 0);
        return res;
    }

    /**
     * 数组index之前的数是已经被排好的，index及其之后的数是可自由排列的
     *
     * @param nums  给定原始数组
     * @param index 全排列第index位置
     */
    public void backtrace(int[] nums, int index) {
        if (index == nums.length) {//全排列所有位置均已决定完成
            res.add(new ArrayList<>(temp));
        }
        for (int i = index; i < nums.length; i++) {
            //全排列当前位置选择数组下标为i的数
            temp.add(nums[i]);
            swap(nums, index, i);
            backtrace(nums, index + 1);//决定全排列下一个位置
            //全排列当前位置不再选择数组下标为i的数
            swap(nums, index, i);
            temp.remove(temp.size() - 1);
        }
    }

    public void swap(int[] nums, int i, int j) {//交换数组中下标i和j的值
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> lists = permute(nums);
        System.out.println(lists);
    }
}
